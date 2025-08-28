package com.edigest.journalApp.service;

import com.edigest.journalApp.Repository.JournalentryRepo;
import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class Jounalentryservice {
@Autowired
    private JournalentryRepo journalentryRepo;
@Autowired
private Userservice userservice;

 private  static final Logger logger = LoggerFactory.getLogger(Jounalentryservice.class);

@Transactional
public void saveEntry(JournalEntry journalEntry, String userName){
    try {
        User user= userservice.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalentryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userservice.saveuser(user);
    }catch (Exception e){
        System.out.println(e);
        throw new RuntimeException("an error is thrown");
    }

}

    public void saveEntry(JournalEntry journalEntry){
        journalentryRepo.save(journalEntry);
    }
public List<JournalEntry> getAll(){
    return journalentryRepo.findAll();
}

public Optional<JournalEntry> findbyId(ObjectId id){
    return journalentryRepo.findById(id);
}
public boolean deletebyid(ObjectId id, String userName){
    boolean removed = false;
    try {
        User user = userservice.findByUserName(userName);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userservice.saveuser(user);
            journalentryRepo.deleteById(id);
        }

    }catch (Exception e){
logger.info("Info");
logger.error("Error",e);
        throw new RuntimeException("An error",e);
    }

    return removed;


}


}
