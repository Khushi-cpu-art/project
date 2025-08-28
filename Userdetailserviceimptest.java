package com.edigest.journalApp.Service;

import com.edigest.journalApp.Repository.UserRepo;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.Userdetailserviceimp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.mockito.Mockito.when;


public class Userdetailserviceimptest {

    @InjectMocks
    private Userdetailserviceimp userdetailserviceimp;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loaduserbyusername() {
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("inrinrick").roles(Collections.singletonList("ADMIN")).build());

        UserDetails userDetails = userdetailserviceimp.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }
}
