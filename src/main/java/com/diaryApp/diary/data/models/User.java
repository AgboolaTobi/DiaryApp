package com.diaryApp.diary.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private Diary diary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
