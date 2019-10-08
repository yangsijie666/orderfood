package xyz.diaodeyipi.orderfood.controller;

import org.springframework.web.bind.annotation.*;
import xyz.diaodeyipi.orderfood.entity.OrderDetail;
import xyz.diaodeyipi.orderfood.service.OrderDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangsijie
 */
@RestController("orderDetailController")
@RequestMapping(path = "/orderDetail")
public class OrderDetailController {
    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping(path = "/list")
    public List<OrderDetail> selectAll() {
        return orderDetailService.selectAll();
    }

    @GetMapping(path = "/list/{orderId}")
    public List<OrderDetail> selectByOrderId(@PathVariable("orderId") int orderId) {
        return orderDetailService.selectByOrderId(orderId);
    }

    @PostMapping(path = "/")
    public String insert(@RequestParam(value = "deskId", required = true) int deskId,
                         @RequestParam(value = "menuId", required = true) int menuId,
                         @RequestParam(value = "num", required = true) int num) {
        int result = orderDetailService.insert(deskId, menuId, num);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @DeleteMapping(path = "/")
    public String delete(OrderDetail orderDetail) {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("orderId", orderDetail.getOrderId());
        map.put("menuId", orderDetail.getMenuId());
        orderDetailService.delete(map);
        return "success";
    }

    @DeleteMapping(path = "/{orderId}")
    public String deleteByOrderId(@PathVariable("orderId") int orderId) {
        orderDetailService.deleteByOrderId(orderId);
        return "success";
    }
}
