package com.edigest.journalApp.scheduler;

import com.edigest.journalApp.Repository.usersentclassimpl;
import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Sentiment;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.Emailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private Emailservice emailservice;

    @Autowired
    private usersentclassimpl userdetailserviceimp;


    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchuserandsendmail(){
        List<User> userForSa = userdetailserviceimp.getUserForSa();
        for(User user:userForSa){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment>  sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getContent()).collect(Collectors.toList()).reversed();
            Map<Sentiment,Integer> sentimentCount = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment != null){
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0) + 1);
                }
            }
            Sentiment mostFrequestsentiment = null;
            int maxcount = 0;
            for(Map.Entry<Sentiment,Integer> entry: sentimentCount.entrySet()){
                if(entry.getValue() > maxcount){
                    maxcount = entry.getValue();
                    mostFrequestsentiment = entry.getKey();

                }
            }
            if(mostFrequestsentiment != null){
                emailservice.sendEmail(user.getEmail(),"Sentiment of last 7 days", mostFrequestsentiment.toString());
            }





    }
}
