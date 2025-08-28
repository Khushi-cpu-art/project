package com.edigest.journalApp.cache;

import com.edigest.journalApp.Repository.Configenapprepo;
import com.edigest.journalApp.entity.Configenapp;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Appcache {

    @Autowired
    private Configenapprepo configenapprepo;

    public Map<String, String> appcache;

    @PostConstruct
    public void init(){
        List<Configenapp> all = configenapprepo.findAll();
       for (Configenapp configenapp : all){
           appcache.put(configenapp.getKey(),configenapp.getValue());
       }
        appcache=null;
    }
}
