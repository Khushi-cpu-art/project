package com.edigest.journalApp.healthcheck;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class Publiccontroller {
    @Autowired
    private Userservice userservice;
   @GetMapping("/health-check")
    public String healthcheck() {
        return "OK";
    }
    @PostMapping
    public void createUser(@RequestBody User user){
        userservice.saveuser(user);
    }
}
