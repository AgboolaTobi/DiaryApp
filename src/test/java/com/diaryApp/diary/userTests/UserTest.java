package com.diaryApp.diary.userTests;

import com.diaryApp.diary.data.models.DiaryCategory;
import com.diaryApp.diary.dtos.requests.*;
import com.diaryApp.diary.dtos.responses.*;
import com.diaryApp.diary.exceptions.DiaryNotFoundException;
import com.diaryApp.diary.exceptions.EntryNotFoundException;
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
        request.setUserId("668a73b093851159dd3e5781");

        GetUserDiaryResponse response = userService.getAllUserDiary(request);
        System.out.println(response);
        assertThat(response).isNotNull();

    }

    @Test
    public void testThatAUserCanCreateEntryInADiary() throws UserNotFoundException, DiaryNotFoundException {
        EntryCreationRequest request = new EntryCreationRequest();
        request.setUserId("668a73b093851159dd3e5781");
        request.setDiaryId("668a73b093851159dd3e5780");
        request.setTitle("Primary Education");
        request.setContent("Attended- Early Life Kiddies College, Osogbo. Between 2000 to 2004");

        EntryCreationResponse response = userService.createEntry(request);
        assertThat(response).isNotNull();

    }

    @Test
    public void testThatAUserCanCreateMultipleEntriesInADiary() throws UserNotFoundException, DiaryNotFoundException {
        EntryCreationRequest request = new EntryCreationRequest();
        request.setUserId("668a73b093851159dd3e5781");
        request.setDiaryId("668a73b093851159dd3e5780");
        request.setTitle("Secondary Education");
        request.setContent("Attended Fakunle Comprehensive High School, Osogbo. Between from 2004 to 2010.");

        EntryCreationResponse response = userService.createEntry(request);
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatAUserCanMakeEntryInMultipleDiaries() throws UserNotFoundException, DiaryNotFoundException {
        EntryCreationRequest request = new EntryCreationRequest();
        request.setUserId("668a73b093851159dd3e5781");
        request.setDiaryId("668a7500a2536d1012d2b10b");
        request.setTitle("Family matters");
        request.setContent("From a polygamous family of 13. One Dad, three mums and nine children. I'm the 8th of the 9...");

        EntryCreationResponse response = userService.createEntry(request);
        assertThat(response).isNotNull();
    }

    @Test
    public  void testThatAUserCanViewAllEntriesInAPArticularDiary() throws UserNotFoundException, EntryNotFoundException, DiaryNotFoundException {
        ViewAllDiaryEntriesRequest request = new ViewAllDiaryEntriesRequest();
        request.setUserId("668a73b093851159dd3e5781");
        request.setDiaryId("668a73b093851159dd3e5780");
        ViewAllDiaryEntriesResponse response = userService.viewDiary(request);
        System.out.println(response);
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatAUserCanViewASpecificEntryInASpecificDiary() throws UserNotFoundException, DiaryNotFoundException {
        ViewAnEntryInADiaryRequest request = new ViewAnEntryInADiaryRequest();
        request.setUserId("668a73b093851159dd3e5781");
        request.setDiaryId("668a73b093851159dd3e5780");
        request.setEntryId("668a756225dad87e4de659f2");

        ViewAnEntryInADiaryResponse response = userService.viewDiaryEntry(request);
        System.out.println(response);
        assertThat(response).isNotNull();


    }

    @Test
    public void testThatAUserCanDeleteASpecificDiaryEntry() {
        DeleteEntryRequest request = new DeleteEntryRequest();
        request.setUserId("668a73b093851159dd3e5781");
        request.setDiaryId("668a73b093851159dd3e5780");
        request.setEntryId("668a756225dad87e4de659f2");

        DeleteEntryResponse response = userService.deleteEntry(request);
        assertThat(response).isNotNull();



    }
}
