package com.example.StockMarket;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("addUser")
    public String addUser(@RequestBody User user) throws Exception{
        return userService.addUser(user);
    }

    @GetMapping("login")
    public String auth(@RequestBody Login login){
        return userService.auth(login);
    }

    @GetMapping("getinfo")
    public String getInfo(@RequestBody Stocks stocks) throws MessagingException {
        return userService.getInfo(stocks);
    }

    @GetMapping("infoinrange")
    public String abc(@RequestBody InfoInRange infoInRange){
        return userService.abc(infoInRange);
    }



}
