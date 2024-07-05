package com.diaryApp.diary.services;

import com.diaryApp.diary.dtos.requests.UserRegistrationRequest;
import com.diaryApp.diary.dtos.responses.UserRegistrationResponse;
import com.diaryApp.diary.exceptions.UserExistException;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request) throws UserExistException;
}
