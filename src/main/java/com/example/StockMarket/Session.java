package com.example.StockMarket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "sessions" )
public class Session {
    @Id
    private String sessionId;
    private LocalDateTime creationTime;
    private String email; //user email
    private List<String> info;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public Session() {
        this.creationTime = LocalDateTime.now();
        this.sessionId = UUID.randomUUID().toString();
    }


    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getSessionId() {
        return sessionId;
    }
}
