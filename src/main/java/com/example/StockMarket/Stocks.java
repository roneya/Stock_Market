package com.example.StockMarket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "stocks")

public class Stocks {
    @Id
    private int id;
    private static int count = 1;
    private List<String> stock_symbol;

    public List<String> getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(List<String> stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    private List<Notification_frequency> notificationFrequency;
    private String notify_time;

    private String email; //I added this as login info than doing it differently
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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


    public List<Notification_frequency> getNotificationFrequency() {
        return notificationFrequency;
    }

    public void setNotificationFrequency(List<Notification_frequency> notificationFrequency) {
        this.notificationFrequency = notificationFrequency;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }
}
