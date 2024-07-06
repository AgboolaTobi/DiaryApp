package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Diary;
import com.diaryApp.diary.data.models.DiaryCategory;
import com.diaryApp.diary.data.models.User;
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

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request) throws UserExistException;

    UserAddDiaryResponse addDiary(UserAddDiaryRequest request) throws UserNotFoundException;

    GetUserDiaryResponse getAllUserDiary(GetUserDiaryRequest request) throws UserNotFoundException;

    User findById(String userId);


    void save(User existingUser);

    EntryCreationResponse createEntry(EntryCreationRequest request) throws UserNotFoundException, DiaryNotFoundException;
}
