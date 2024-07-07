package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Diary;
import com.diaryApp.diary.data.models.DiaryCategory;
import com.diaryApp.diary.data.models.User;
import com.diaryApp.diary.dtos.requests.*;
import com.diaryApp.diary.dtos.responses.*;
import com.diaryApp.diary.exceptions.DiaryNotFoundException;
import com.diaryApp.diary.exceptions.EntryNotFoundException;
import com.diaryApp.diary.exceptions.UserExistException;
import com.diaryApp.diary.exceptions.UserNotFoundException;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request) throws UserExistException;

    UserAddDiaryResponse addDiary(UserAddDiaryRequest request) throws UserNotFoundException;

    GetUserDiaryResponse getAllUserDiary(GetUserDiaryRequest request) throws UserNotFoundException;

    User findById(String userId);


    void save(User existingUser);

    EntryCreationResponse createEntry(EntryCreationRequest request) throws UserNotFoundException, DiaryNotFoundException;

    ViewAllDiaryEntriesResponse viewDiary(ViewAllDiaryEntriesRequest request) throws UserNotFoundException, EntryNotFoundException, DiaryNotFoundException;
}
