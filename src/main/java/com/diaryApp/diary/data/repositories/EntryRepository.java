package com.diaryApp.diary.data.repositories;

import com.diaryApp.diary.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository extends MongoRepository<Entry, String> {
}
