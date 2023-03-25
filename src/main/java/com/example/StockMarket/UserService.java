package com.example.StockMarket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "Done";
    }

    public String auth(Login login) {
        User user = userRepository.findById(login.getEmail()).get();
        if (login.getPassword().equals(login.getPassword()))
            return "Login Successful";
        return "Please Enter Valid credentials";

    }


    private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_{frq}&symbol={symbol}&apikey={apikey}";

    public String getInfo(Stocks stocks) {
        String key = "ND0M9WQI5K7TN5KE";
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = API_URL.replace("{symbol}", stocks.getStock_symbol()).replace("{frq}", stocks.getNotificationFrequency().toString()).replace("{apikey}", key);

        String response = restTemplate.getForObject(apiUrl, String.class);

        return response;
    }


}