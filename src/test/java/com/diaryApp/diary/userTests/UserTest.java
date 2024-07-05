package com.diaryApp.diary.userTests;

import com.diaryApp.diary.dtos.requests.UserRegistrationRequest;
import com.diaryApp.diary.dtos.responses.UserRegistrationResponse;
import com.diaryApp.diary.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testThatAUserCanRegister(){
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("tobi4tee@gmail.com");
        request.setPassword("123456");
        request.setUsername("agboolatoby");

        UserRegistrationResponse response = userService.register(request);


    }
}
