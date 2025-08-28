package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.Jounalentryservice;
import com.edigest.journalApp.service.Userservice;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class Journalentrycontrollerv2 {
 @Autowired
 private Jounalentryservice jounalentryservice;

 @Autowired
 private Userservice userservice;

    @GetMapping
    public ResponseEntity<?> getAllJournalentry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userservice.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!= null && all.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry, @PathVariable String userName) {
        User user = userservice.findByUserName(userName);
        jounalentryservice.saveEntry(myentry,userName);
        return new ResponseEntity<>(myentry, HttpStatus.CREATED);
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getjournalbtid(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userservice.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = jounalentryservice.findbyId(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deletebyid(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = jounalentryservice.deletebyid(myid,userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> update(@PathVariable ObjectId myid, @RequestBody JournalEntry myentry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userservice.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = jounalentryservice.findbyId(myid);

            if(journalEntry.isPresent()){

                if(journalEntry != null){
                    JournalEntry old =journalEntry.get();
                    old.setTitle(myentry.getTitle() != null && myentry.getTitle().equals("") ? myentry.getTitle() : old.getTitle());
                    old.setContent(myentry.getContent() != null && myentry.getContent().equals("")? myentry.getContent() : old.getContent());
                    jounalentryservice.saveEntry(old);
                    return  new ResponseEntity<>(old,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }




        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
