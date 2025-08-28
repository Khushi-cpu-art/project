package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private Userservice userservice;

    @GetMapping("/all-users")
    public ResponseEntity<?> getalluser(){
        List<User> all = userservice.getAll();
        if(all!= null && !all.isEmpty() ){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userservice.saveAdmin(user);
    }

}
