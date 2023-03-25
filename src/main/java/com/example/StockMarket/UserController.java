package com.example.StockMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("addUser")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


}
