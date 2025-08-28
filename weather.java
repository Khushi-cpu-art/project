package com.edigest.journalApp.service;

import com.edigest.journalApp.apiresponse.weatherresponse;
import com.edigest.journalApp.cache.Appcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weather {
    @Value("${weather.api.key}")
    private String apikey;

    @Autowired
    private Appcache appcache;

    @Autowired
    private static RestTemplate restTemplate;
    public weatherresponse getWeather(String city) {
String finalapi =  appcache.appcache.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<weatherresponse> responseEntity= restTemplate.exchange(finalapi, HttpMethod.GET, null, weatherresponse.class);
        weatherresponse body = responseEntity.getBody();
    return body;
    }

}
