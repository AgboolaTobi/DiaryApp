package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.User;
import com.diaryApp.diary.data.repositories.UserRepository;
import com.diaryApp.diary.dtos.requests.UserRegistrationRequest;
import com.diaryApp.diary.dtos.responses.UserRegistrationResponse;
import com.diaryApp.diary.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServciceApp implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) throws UserExistException {
        User isRegistered = userRepository.findByEmail(request.getEmail()) != true;
        if (isRegistered) throw new UserExistException("");
    }
}
