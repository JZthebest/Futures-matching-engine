package com.zyx.matchingengine.service;

import com.zyx.matchingengine.engine.OrderBook;
import com.zyx.matchingengine.model.Order;
import com.zyx.matchingengine.model.Trade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderBook orderBook = new OrderBook();

    public void submitOrder(Order order) {
        orderBook.submit(order);
    }

    public List<Trade> getAllTrades() {
        return orderBook.getTrades();
    }
}
