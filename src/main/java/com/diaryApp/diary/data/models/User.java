package com.diaryApp.diary.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
//    private boolean isLogin;
    private List<Diary> diaries;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
