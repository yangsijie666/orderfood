package xyz.diaodeyipi.orderfood.service;

import xyz.diaodeyipi.orderfood.entity.Order;

import java.util.List;

/**
 * @author yangsijie
 */
public interface OrderService {
    List<Order> selectAll();
    Order selectOne(int deskId);
    int insert(Order order);
    int delete(int deskId);
}
