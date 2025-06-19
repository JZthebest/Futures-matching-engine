package com.zyx.matchingengine.engine;

import com.zyx.matchingengine.model.Order;
import com.zyx.matchingengine.model.Trade;
import java.util.*;

public class OrderBook {

    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(
            (o1, o2) -> o1.price != o2.price ?
                    Double.compare(o2.price, o1.price) : Long.compare(o1.timestamp, o2.timestamp)
    );

    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(
            (o1, o2) -> o1.price != o2.price ?
                    Double.compare(o1.price, o2.price) : Long.compare(o1.timestamp, o2.timestamp)
    );

    private final List<Trade> trades = new ArrayList<>();

    public void submit(Order order) {
        if (order.side == Order.Side.BUY) {
            matchBuyOrder(order);
        } else {
            matchSellOrder(order);
        }
    }

    private void matchBuyOrder(Order buy) {
        while (!sellOrders.isEmpty() && buy.quantity > 0) {
            Order sell = sellOrders.peek();
            if (sell.price > buy.price) break;

            double tradedQty = Math.min(buy.quantity, sell.quantity);
            buy.quantity -= tradedQty;
            sell.quantity -= tradedQty;

            trades.add(new Trade(buy.id, sell.id, sell.price, tradedQty));

            if (sell.quantity == 0) sellOrders.poll();
        }

        if (buy.quantity > 0) buyOrders.offer(buy);
    }

    private void matchSellOrder(Order sell) {
        while (!buyOrders.isEmpty() && sell.quantity > 0) {
            Order buy = buyOrders.peek();
            if (buy.price < sell.price) break;

            double tradedQty = Math.min(buy.quantity, sell.quantity);
            sell.quantity -= tradedQty;
            buy.quantity -= tradedQty;

            trades.add(new Trade(buy.id, sell.id, buy.price, tradedQty));

            if (buy.quantity == 0) buyOrders.poll();
        }

        if (sell.quantity > 0) sellOrders.offer(sell);
    }

    public void printTrades() {
        for (Trade t : trades) {
            System.out.println(t);
        }
    }
}
