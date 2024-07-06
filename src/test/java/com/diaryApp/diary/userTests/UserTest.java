package com.diaryApp.diary.userTests;

import com.diaryApp.diary.data.models.DiaryCategory;
import com.diaryApp.diary.dtos.requests.EntryCreationRequest;
import com.diaryApp.diary.dtos.requests.GetUserDiaryRequest;
import com.diaryApp.diary.dtos.requests.UserAddDiaryRequest;
import com.diaryApp.diary.dtos.requests.UserRegistrationRequest;
import com.diaryApp.diary.dtos.responses.EntryCreationResponse;
import com.diaryApp.diary.dtos.responses.GetUserDiaryResponse;
import com.diaryApp.diary.dtos.responses.UserAddDiaryResponse;
import com.diaryApp.diary.dtos.responses.UserRegistrationResponse;
import com.diaryApp.diary.exceptions.DiaryNotFoundException;
import com.diaryApp.diary.exceptions.UserExistException;
import com.diaryApp.diary.exceptions.UserNotFoundException;
import com.diaryApp.diary.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testThatAUserCanRegister() throws UserExistException {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("tobi4tee@gmail.com");
        request.setPassword("123456");
        request.setUsername("agboolatoby");
        request.setDiaryName("My Education Journey");
        request.setDiaryCategory(DiaryCategory.EDUCTION);
        request.setDiaryDescription("Brief Story Of My Academic Journey");

        UserRegistrationResponse response = userService.register(request);

        assertThat(response).isNotNull();

    }

    @Test
    public void testThatAnExistingUserCanCreateMoreThanOneDiaryCategory() throws UserNotFoundException {
        UserAddDiaryRequest request = new UserAddDiaryRequest();
        request.setEmail("tobi4tee@gmail.com");
        request.setDiaryName("Family History");
        request.setDiaryCategory(DiaryCategory.FAMILY);
        request.setDiaryDescription("Family they say is everything. Here's a brief story about my family");

        UserAddDiaryResponse response = userService.addDiary(request);

        assertThat(response).isNotNull();

    }
    @Test
    public void testThatAListOfUserExistingDiaryCanBeRetrieved() throws UserNotFoundException {
        GetUserDiaryRequest request = new GetUserDiaryRequest();
        request.setUserId("6688aee8124da65ae405e0ea");

        GetUserDiaryResponse response = userService.getAllUserDiary(request);
        System.out.println(response);
        assertThat(response).isNotNull();

    }

    @Test
    public void testThatAUserCanCreateEntryInADiary() throws UserNotFoundException, DiaryNotFoundException {
        EntryCreationRequest request = new EntryCreationRequest();
        request.setUserId("6688aee8124da65ae405e0ea");
        request.setDiaryId("6688aee8124da65ae405e0e9");
        request.setTitle("Primary Education");
        request.setContent("Attended- Early Life Kiddies College, Osogbo. Between 2000 to 2004");

        EntryCreationResponse response = userService.createEntry(request);
        assertThat(response).isNotNull();

    }
}
