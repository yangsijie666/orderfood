package xyz.diaodeyipi.orderfood.dao;

import org.springframework.stereotype.Repository;
import xyz.diaodeyipi.orderfood.entity.Order;

import java.util.List;

/**
 * @author yangsijie
 */
@Repository("orderDao")
public interface OrderDao {
    List<Order> selectAll();
    Order selectOne(int deskId);
    Order selectById(int id);
    int insert(Order order);
    int update(Order order);
    int delete(int deskId);
}
