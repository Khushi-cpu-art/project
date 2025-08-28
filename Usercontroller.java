package com.edigest.journalApp.controller;

import com.edigest.journalApp.Repository.UserRepo;
import com.edigest.journalApp.apiresponse.weatherresponse;
import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.Jounalentryservice;
import com.edigest.journalApp.service.Userservice;
import com.edigest.journalApp.service.weather;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class Usercontroller {
 @Autowired
 private Userservice userservice;

 @Autowired
 private weather weathers;

 @GetMapping
 public List<User> getAllUser(){
     return userservice.getAll();
    }



@PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();

     User userindb =  userservice.findByUserName(userName);
if(userindb!=null){
    userindb.setUserName(user.getUserName());
    userindb.setPassword(user.getPassword());
userservice.saveuser(userindb);
}
return new ResponseEntity<>(HttpStatus.NOT_FOUND);


}

    @GetMapping("/greetings")
    ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        weatherresponse weatherresponse = weathers.getWeather("Mumbai");
        String greeting = "";
        if(weatherresponse!= null){
            greeting = ", weather" + weatherresponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("hi" + authentication.getName(),HttpStatus.OK);


    }
}
