package com.diaryApp.diary.dtos.requests;

import com.diaryApp.diary.data.models.DiaryCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String diaryName;
    private String diaryDescription;
    private DiaryCategory diaryCategory;
}
