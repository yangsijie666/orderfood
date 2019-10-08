package xyz.diaodeyipi.orderfood.service.impl;

import org.springframework.stereotype.Service;
import xyz.diaodeyipi.orderfood.dao.MenuDao;
import xyz.diaodeyipi.orderfood.dao.OrderDao;
import xyz.diaodeyipi.orderfood.dao.OrderDetailDao;
import xyz.diaodeyipi.orderfood.entity.Menu;
import xyz.diaodeyipi.orderfood.entity.Order;
import xyz.diaodeyipi.orderfood.entity.OrderDetail;
import xyz.diaodeyipi.orderfood.service.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangsijie
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao;
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;

    public MenuServiceImpl(MenuDao menuDao, OrderDao orderDao, OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
        this.menuDao = menuDao;
        this.orderDao = orderDao;
    }

    @Override
    public List<Menu> selectAll() {
        return menuDao.selectAll();
    }

    @Override
    public Menu selectOne(String name) {
        return menuDao.selectOne(name);
    }

    @Override
    public int insert(Menu menu) {
        return menuDao.insert(menu);
    }

    @Override
    public int update(Menu menu) {
        Menu oldMenu = menuDao.selectOne(menu.getName());
        List<OrderDetail> orderDetails = orderDetailDao.selectByMenuId(oldMenu.getId());
        for (OrderDetail orderDetail : orderDetails) {
            double priceDifference = (menu.getPrice() - oldMenu.getPrice()) * orderDetail.getNum();
            Order order = orderDao.selectById(orderDetail.getOrderId());
            order.setTotalPrice(order.getTotalPrice() + priceDifference);
            orderDao.update(order);
        }
        return menuDao.update(menu);
    }

    @Override
    public int delete(String name) {
        Menu oldMenu = menuDao.selectOne(name);
        List<OrderDetail> orderDetails = orderDetailDao.selectByMenuId(oldMenu.getId());
        for (OrderDetail orderDetail : orderDetails) {
            Map<String, Integer> map = new HashMap<>(2);
            map.put("orderId", orderDetail.getOrderId());
            map.put("menuId", orderDetail.getMenuId());
            orderDetailDao.delete(map);

            double priceDifference = -1 * (oldMenu.getPrice()) * orderDetail.getNum();
            Order order = orderDao.selectById(orderDetail.getOrderId());
            double newPrice = order.getTotalPrice() + priceDifference;
            if (newPrice >= -0.000001 && newPrice <= 0.000001) {
                orderDao.delete(order.getDeskId());
            } else {
                order.setTotalPrice(newPrice);
                orderDao.update(order);
            }
        }
        return menuDao.delete(name);
    }
}
