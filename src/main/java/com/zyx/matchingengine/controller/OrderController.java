package com.zyx.matchingengine.controller;

import com.zyx.matchingengine.model.Order;
import com.zyx.matchingengine.model.Trade;
import com.zyx.matchingengine.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void submitOrder(@RequestBody Order order) {
        orderService.submitOrder(order);
    }

    @GetMapping("/trades")
    public List<Trade> getAllTrades() {
        return orderService.getAllTrades();
    }
}
