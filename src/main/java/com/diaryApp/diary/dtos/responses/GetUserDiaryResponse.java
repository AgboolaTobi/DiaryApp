package com.diaryApp.diary.dtos.responses;

import com.diaryApp.diary.data.models.Diary;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class GetUserDiaryResponse {
    private List<Diary> userDiaries;
    private String message;
}
