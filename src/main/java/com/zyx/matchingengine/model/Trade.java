package com.zyx.matchingengine.model;

public class Trade {
    public final long buyOrderId;
    public final long sellOrderId;
    public final double price;
    public final double quantity;

    public Trade(long buyOrderId, long sellOrderId, double price, double quantity) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TRADE: " + quantity + " @ " + price + " between BUY " + buyOrderId + " and SELL " + sellOrderId;
    }
}
