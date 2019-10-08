package xyz.diaodeyipi.orderfood.service.impl;

import org.springframework.stereotype.Service;
import xyz.diaodeyipi.orderfood.dao.MenuDao;
import xyz.diaodeyipi.orderfood.dao.OrderDao;
import xyz.diaodeyipi.orderfood.dao.OrderDetailDao;
import xyz.diaodeyipi.orderfood.entity.Order;
import xyz.diaodeyipi.orderfood.entity.OrderDetail;
import xyz.diaodeyipi.orderfood.service.OrderDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangsijie
 */
@Service("orderDetail")
public class OrderDetailServiceImpl implements OrderDetailService {
    private MenuDao menuDao;
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;

    public OrderDetailServiceImpl(MenuDao menuDao, OrderDao orderDao, OrderDetailDao orderDetailDao) {
        this.menuDao = menuDao;
        this.orderDao = orderDao;
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public List<OrderDetail> selectAll() {
        return orderDetailDao.selectAll();
    }

    @Override
    public List<OrderDetail> selectByOrderId(int orderId) {
        return orderDetailDao.selectByOrderId(orderId);
    }

    @Override
    public int insert(int deskId, int menuId, int num) {
        double priceDifference = menuDao.selectById(menuId).getPrice() * num;
        Order order = orderDao.selectOne(deskId);

        if (order == null) {
            order = new Order(deskId, priceDifference);
            orderDao.insert(order);
            order = orderDao.selectOne(deskId);
        } else {
            order.setTotalPrice(order.getTotalPrice() + priceDifference);
            orderDao.update(order);
        }

        Map<String, Integer> map = new HashMap<>(2);
        map.put("orderId", order.getId());
        map.put("menuId", menuId);
        OrderDetail orderDetail = orderDetailDao.selectOne(map);
        if (orderDetail == null) {
            orderDetail = new OrderDetail(order.getId(), menuId, num);
            return orderDetailDao.insert(orderDetail);
        } else {
            orderDetail.setNum(orderDetail.getNum() + num);
            return orderDetailDao.update(orderDetail);
        }
    }

    @Override
    public int update(int deskId, OrderDetail orderDetail) {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("orderId", orderDetail.getOrderId());
        map.put("menuId", orderDetail.getMenuId());
        OrderDetail oldOrderDetail = orderDetailDao.selectOne(map);

        double menuPrice = menuDao.selectById(orderDetail.getMenuId()).getPrice();
        double priceDifference = menuPrice * (oldOrderDetail.getNum() - orderDetail.getNum());

        Order order = orderDao.selectOne(deskId);

        if (order == null) {
            order = new Order(deskId, priceDifference);
            orderDao.insert(order);
        } else {
            order.setTotalPrice(order.getTotalPrice() + priceDifference);
            orderDao.update(order);
        }

        return orderDetailDao.update(orderDetail);
    }

    @Override
    public int delete(Map<String, Integer> map) {
        Order order = orderDao.selectById(map.get("orderId"));
        OrderDetail orderDetail = orderDetailDao.selectOne(map);

        int result = orderDetailDao.delete(map);

        double menuPrice = menuDao.selectById(orderDetail.getMenuId()).getPrice();
        double priceDifference = -1 * (orderDetail.getNum() * menuPrice);
        double newPrice = order.getTotalPrice() + priceDifference;
        if (newPrice >= -0.000001 && newPrice <= 0.000001) {
            orderDao.delete(order.getDeskId());
        } else {
            order.setTotalPrice(order.getTotalPrice() + priceDifference);
            orderDao.update(order);
        }

        return result;
    }

    @Override
    public int
    deleteByOrderId(int orderId) {
        Order order = orderDao.selectById(orderId);
        List<OrderDetail> orderDetails = orderDetailDao.selectByOrderId(orderId);
        int result = orderDetailDao.deleteByOrderId(orderId);

        for (OrderDetail orderDetail : orderDetails) {
            double menuPrice = menuDao.selectById(orderDetail.getMenuId()).getPrice();
            double priceDifference = -1 * (orderDetail.getNum() * menuPrice);
            double newPrice = order.getTotalPrice() + priceDifference;
            if (newPrice >= -0.000001 && newPrice <= 0.000001) {
                orderDao.delete(order.getDeskId());
            } else {
                order.setTotalPrice(order.getTotalPrice() + priceDifference);
                orderDao.update(order);
            }
        }

        return result;
    }
}
