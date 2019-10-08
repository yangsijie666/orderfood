package xyz.diaodeyipi.orderfood.service.impl;

import org.springframework.stereotype.Service;
import xyz.diaodeyipi.orderfood.dao.OrderDao;
import xyz.diaodeyipi.orderfood.dao.OrderDetailDao;
import xyz.diaodeyipi.orderfood.entity.Order;
import xyz.diaodeyipi.orderfood.entity.OrderDetail;
import xyz.diaodeyipi.orderfood.service.OrderService;

import java.util.List;

/**
 * @author yangsijie
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;

    public OrderServiceImpl(OrderDao orderDao, OrderDetailDao orderDetailDao) {
        this.orderDao = orderDao;
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public List<Order> selectAll() {
        return orderDao.selectAll();
    }

    @Override
    public Order selectOne(int deskId) {
        return orderDao.selectOne(deskId);
    }

    @Override
    public int insert(Order order) {
        order.setTotalPrice(0);
        return orderDao.insert(order);
    }

    @Override
    public int delete(int deskId) {
        Order order = orderDao.selectOne(deskId);
        orderDetailDao.deleteByOrderId(order.getId());
        return orderDao.delete(deskId);
    }
}
