package com.edigest.journalApp.service;


import com.edigest.journalApp.Repository.UserRepo;

import com.edigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class Userservice {
@Autowired
private UserRepo UserRepo;
private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();
public void savenewUser(User user){
    user.setPassword(passwordencoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("USER"));
     UserRepo.save(user);
}

    public void saveuser(User user){
        UserRepo.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepo.save(user);
    }
public List<User> getAll(){
    return UserRepo.findAll();
}

public Optional<User> findbyId(ObjectId id){
    return UserRepo.findById(id);
}
public void deletebyid(ObjectId id){
    UserRepo.deleteById(id);

}

    public User findByUserName(String userName) {
    return  UserRepo.findByUserName(userName);

    }
}
