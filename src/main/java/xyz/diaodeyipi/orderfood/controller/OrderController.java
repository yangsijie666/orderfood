package xyz.diaodeyipi.orderfood.controller;

import org.springframework.web.bind.annotation.*;
import xyz.diaodeyipi.orderfood.entity.Order;
import xyz.diaodeyipi.orderfood.service.OrderService;

import java.util.List;

/**
 * @author yangsijie
 */
@RestController("orderController")
@RequestMapping(path = "/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/list")
    public List<Order> selectAll() {
        return orderService.selectAll();
    }

    @GetMapping(path = "/list/{deskId}")
    public Order selectOne(@PathVariable("deskId") int deskId) {
        return orderService.selectOne(deskId);
    }

    @PostMapping(path = "/")
    public String insert(Order order) {
        int result = orderService.insert(order);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @DeleteMapping(path = "/{deskId}")
    public String delete(@PathVariable("deskId") int deskId) {
        orderService.delete(deskId);
        return "success";
    }
}
