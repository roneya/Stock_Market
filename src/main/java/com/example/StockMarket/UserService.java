package com.example.StockMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "Done";
    }
}
