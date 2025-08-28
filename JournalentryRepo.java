package com.edigest.journalApp.Repository;


import com.edigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalentryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
