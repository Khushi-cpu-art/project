//package com.edigest.journalApp.Service;
//
//import com.edigest.journalApp.Repository.UserRepo;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.springframework.test.util.AssertionErrors.*;
//
//@SpringBootTest
//public class UserserviceTest {
//
//    @Autowired
//    private UserRepo userRepo;
//
//
//
//
//
//
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Ram"
//    })
//    public void testFindByUserName(String name){
//        assertNotNull("User not null",userRepo.findByUserName("Khushi"));
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2"
//    })
//    public void test(int a,int b, int c){
//        assertEquals(c,a+b);
//    }
//}
