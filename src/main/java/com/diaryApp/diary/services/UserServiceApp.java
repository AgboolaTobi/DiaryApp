
package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Diary;
import com.diaryApp.diary.data.models.Entry;
import com.diaryApp.diary.data.models.User;
import com.diaryApp.diary.data.repositories.DiaryRepository;
import com.diaryApp.diary.data.repositories.UserRepository;
import com.diaryApp.diary.dtos.requests.*;
import com.diaryApp.diary.dtos.responses.*;
import com.diaryApp.diary.exceptions.DiaryNotFoundException;
import com.diaryApp.diary.exceptions.EntryNotFoundException;
import com.diaryApp.diary.exceptions.UserExistException;
import com.diaryApp.diary.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceApp implements UserService{
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryService diaryService;
    private final EntryService entryService;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) throws UserExistException {

        User existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser != null ) throw new UserExistException("User already exists");

        User user = createUser(request);

        List<Diary> userDiaries = new ArrayList<>();

        Diary diary = createDiary(request);


        List<Entry> userEntries = new ArrayList<>();
        diary.setEntries(userEntries);
        diaryService.saveDiary(diary);

        userDiaries.add(diary);
        user.setDiaries(userDiaries);
        userRepository.save(user);



        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setUserId(user.getId());
        response.setMessage("Registration successful");
        return response;
    }

    private static User createUser(UserRegistrationRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    private static Diary createDiary(UserRegistrationRequest request) {
        Diary diary = new Diary();
        diary.setDiaryName(request.getDiaryName());
        diary.setDiaryDescription(request.getDiaryDescription());
        diary.setDiaryCategory(request.getDiaryCategory());
        diary.setCreatedAt(LocalDateTime.now());
        return diary;
    }

    @Override
    public UserAddDiaryResponse addDiary(UserAddDiaryRequest request) throws UserNotFoundException {
        User existingUser = fetchUserByEmail(request.getEmail());
        Diary newDiary = createDiaryFromRequest(request);
        Diary savedDiary = saveDiaryThroughService(newDiary, existingUser);
        updateExistingUserWithNewDiary(existingUser, savedDiary);
        return buildResponse();
    }

    @Override
    public GetUserDiaryResponse getAllUserDiary(GetUserDiaryRequest request) throws UserNotFoundException {
        User existingUser = userRepository.findById(request.getUserId()).orElse(null);
        if (existingUser == null) throw new UserNotFoundException("Invalid user details");

        List<Diary> existingUserDiaries  = existingUser.getDiaries();
        GetUserDiaryResponse response = new GetUserDiaryResponse();
        response.setUserDiaries(existingUserDiaries);
        response.setMessage("Dear, " + "here is a list of your diaries " + existingUserDiaries);
        return response;
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }


    @Override
    public void save(User existingUser) {
        userRepository.save(existingUser);
    }

    @Override
    public EntryCreationResponse createEntry(EntryCreationRequest request) throws UserNotFoundException, DiaryNotFoundException {
        User existingUser = findById(request.getUserId());
        if (existingUser == null) throw new UserNotFoundException("User does not exist");
        List<Diary> existingUserDiaries = existingUser.getDiaries();
        Diary targetDiary = diaryService.findByDiaryId(request.getDiaryId()).orElse(null);
        if (targetDiary == null) throw new DiaryNotFoundException("Diary does not exist");

        Entry entry = new Entry();

        entry.setTitle(request.getTitle());
        entry.setUserId(existingUser.getId());
        entry.setDiaryId(targetDiary.getId());
        entry.setContent(request.getContent());
        entry.setCreatedAt(LocalDateTime.now());
        entryService.saveEntry(entry);
        List<Entry> diaryEntries = targetDiary.getEntries();
        diaryEntries.add(entry);
        targetDiary.setEntries(diaryEntries);
        diaryRepository.save(targetDiary);
        existingUserDiaries.add(targetDiary);
        userRepository.save(existingUser);
        EntryCreationResponse response = new EntryCreationResponse();
        response.setMessage("Entry successfully created");
        return response;

    }

    @Override
    public ViewAllDiaryEntriesResponse viewDiary(ViewAllDiaryEntriesRequest request) throws UserNotFoundException, EntryNotFoundException, DiaryNotFoundException {
        User existingUser = userRepository.findById(request.getUserId()).orElse(null);
        if (existingUser == null) throw new UserNotFoundException("Invalid user details");

        Diary targetDiary = diaryService.findByDiaryId(request.getDiaryId()).orElse(null);
        if (targetDiary == null) throw new DiaryNotFoundException("This diary does not exist or has been deleted");

        return getViewAllDiaryEntriesResponse(targetDiary, existingUser);

    }

    @Override
    public ViewAnEntryInADiaryResponse viewDiaryEntry(ViewAnEntryInADiaryRequest request) throws UserNotFoundException, DiaryNotFoundException {
        User existingUser = userRepository.findById(request.getUserId()).orElse(null);
        if (existingUser == null) throw new UserNotFoundException("Invalid user details");

        Diary targetDiary = diaryService.findByDiaryId(request.getDiaryId()).orElse(null);
        if (targetDiary == null) throw new DiaryNotFoundException("This diary does not exist or has been deleted");

        Entry targetEntry = entryService.findEntryById(request.getEntryId());
        return getViewEntryResponse(targetEntry);
    }

    private static ViewAnEntryInADiaryResponse getViewEntryResponse(Entry targetEntry) {
        ViewAnEntryInADiaryResponse response = new ViewAnEntryInADiaryResponse();
        response.setTargetEntry(targetEntry);
        return response;
    }

    private static ViewAllDiaryEntriesResponse getViewAllDiaryEntriesResponse(Diary targetDiary, User existingUser) throws EntryNotFoundException {
        List<Entry> existingTargetDairyEntries = targetDiary.getEntries();
        if (existingTargetDairyEntries == null) throw new EntryNotFoundException("There are no entries in this diary at the moment...");

        ViewAllDiaryEntriesResponse response = new ViewAllDiaryEntriesResponse();

        response.setMessage("Dear " + existingUser.getUsername() + " " + " here is a list of your entries " + existingTargetDairyEntries);
        response.setDiaryEntries(existingTargetDairyEntries);
        return response;
    }

    private User fetchUserByEmail(String email) throws UserNotFoundException {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new UserNotFoundException("Invalid user details. Kindly register to create diary");
        }
        return existingUser;
    }

    private Diary createDiaryFromRequest(UserAddDiaryRequest request) {
        Diary newDiary = new Diary();
        newDiary.setDiaryName(request.getDiaryName());
        newDiary.setDiaryDescription(request.getDiaryDescription());
        newDiary.setDiaryCategory(request.getDiaryCategory());
        newDiary.setCreatedAt(LocalDateTime.now());
        List<Entry> userEntries = new ArrayList<>();
        newDiary.setEntries(userEntries);
        return newDiary;
    }

    private Diary saveDiaryThroughService(Diary newDiary, User existingUser) {
        newDiary.setUserId(existingUser.getId());
        return diaryService.saveDiary(newDiary);
    }

    private void updateExistingUserWithNewDiary(User existingUser, Diary savedDiary) {
        List<Diary> userDiaries = existingUser.getDiaries();
        userDiaries.add(savedDiary);
        existingUser.setDiaries(userDiaries);
        userRepository.save(existingUser);
    }

    private UserAddDiaryResponse buildResponse() {
        UserAddDiaryResponse response = new UserAddDiaryResponse();
        response.setMessage("Diary successfully added");
        return response;
    }



}
