package com.diaryApp.diary.exceptions;

import java.lang.constant.ConstantDesc;

public class UserExistException extends Exception {
    public UserExistException(ConstantDesc message) {
        super(message.toString());
    }
}
