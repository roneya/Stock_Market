package com.example.StockMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("addUser")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("login")
    public String auth(@RequestBody Login login){
        return userService.auth(login);
    }

    @GetMapping("getinfo")
    public String getInfo(@RequestBody Stocks stocks){
        return userService.getInfo(stocks);
    }



}
