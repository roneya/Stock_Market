package com.example.StockMarket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")

public class Stocks {
    @Id
    private int id;
    private static int count = 1;
    private String stock_symbol;
    private Notification_frequency notificationFrequency;
    private String notify_time;

}
