package xyz.diaodeyipi.orderfood.service;

import xyz.diaodeyipi.orderfood.entity.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * @author yangsijie
 */
public interface OrderDetailService {
    List<OrderDetail> selectAll();
    List<OrderDetail> selectByOrderId(int orderId);
    int insert(int deskId, int menuId, int num);
    int update(int deskId, OrderDetail orderDetail);
    int delete(Map<String, Integer> map);
    int deleteByOrderId(int orderId);
}
