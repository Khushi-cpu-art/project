package com.edigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    private String email;
    private boolean sentiments;
    @Indexed(unique = true)
    @NonNull
 private  String userName;
    @NonNull
 private String password;
    private  List<String> roles;




    @DBRef //user k andr journal entry ka reference create kre h
    private List<JournalEntry> journalEntries = new ArrayList<>();


}