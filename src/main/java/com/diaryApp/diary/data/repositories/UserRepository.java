package com.diaryApp.diary.data.repositories;

import com.diaryApp.diary.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);


}
