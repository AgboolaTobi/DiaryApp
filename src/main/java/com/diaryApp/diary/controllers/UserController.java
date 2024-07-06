//package com.diaryApp.diary.controllers;
//
//import com.diaryApp.diary.dtos.requests.GetUserDiaryRequest;
//import com.diaryApp.diary.dtos.requests.UserAddDiaryRequest;
//import com.diaryApp.diary.dtos.requests.UserRegistrationRequest;
//import com.diaryApp.diary.dtos.responses.GetUserDiaryResponse;
//import com.diaryApp.diary.dtos.responses.UserAddDiaryResponse;
//import com.diaryApp.diary.dtos.responses.UserRegistrationResponse;
//import com.diaryApp.diary.exceptions.UserExistException;
//import com.diaryApp.diary.exceptions.UserNotFoundException;
//import com.diaryApp.diary.services.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/v1/user/")
//@AllArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping("register")
//    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest request) throws UserExistException {
//        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
//    }
//    @PostMapping("addDiary")
//    public ResponseEntity<UserAddDiaryResponse> addDiary(@RequestBody UserAddDiaryRequest request) throws UserNotFoundException {
//        return new ResponseEntity<>(userService.addDiary(request),HttpStatus.CREATED);
//    }
//    @GetMapping("getAllUserDiary")
//    public ResponseEntity<GetUserDiaryResponse> getAllUserDiary(@RequestBody GetUserDiaryRequest request) throws UserNotFoundException {
//        return new ResponseEntity<>(userService.getAllUserDiary(request),HttpStatus.OK);
//    }
//}
