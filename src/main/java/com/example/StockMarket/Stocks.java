package com.example.StockMarket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")

public class Stocks {
    @Id
    private int id; // ND0M9WQI5K7TN5KE
    private static int count = 1;
    private String stock_symbol;

    private Notification_frequency notificationFrequency;
    private String notify_time;
    public Stocks(){
        this.id = count++;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Stocks.count = count;
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    public Notification_frequency getNotificationFrequency() {
        return notificationFrequency;
    }

    public void setNotificationFrequency(Notification_frequency notificationFrequency) {
        this.notificationFrequency = notificationFrequency;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }
}
