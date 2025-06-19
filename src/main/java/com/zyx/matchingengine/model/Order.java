package com.zyx.matchingengine.model;

public class Order {
    public enum Side {BUY, SELL}
    private static long nextId = 1;
    public final long id;
    public final String userId;
    public final Side side;
    public final String symbol;  // ES/NQ/BTC
    public final double price;
    public double quantity;
    public final long timestamp;

    public Order(long id, String userId, Side side, String symbol, double price) {
        this.id = id;
        this.userId = userId;
        this.side = side;
        this.symbol = symbol;
        this.price = price;
        this.timestamp = System.nanoTime();
    }

    @Override
    public String toString() {
        return "[" + id + " " + side + " " + quantity + "@" + price + "]";
    }



}
