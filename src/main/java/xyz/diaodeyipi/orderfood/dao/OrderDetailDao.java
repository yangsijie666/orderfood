package xyz.diaodeyipi.orderfood.dao;

import org.springframework.stereotype.Repository;
import xyz.diaodeyipi.orderfood.entity.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * @author yangsijie
 */
@Repository("orderDetailDao")
public interface OrderDetailDao {
    List<OrderDetail> selectAll();
    List<OrderDetail> selectByMenuId(int menuId);
    List<OrderDetail> selectByOrderId(int orderId);
    OrderDetail selectOne(Map<String, Integer> map);
    int insert(OrderDetail orderDetail);
    int update(OrderDetail orderDetail);
    int delete(Map<String, Integer> map);
    int deleteByOrderId(int orderId);
}
