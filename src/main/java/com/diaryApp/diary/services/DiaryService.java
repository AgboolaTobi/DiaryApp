package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Diary;
import com.diaryApp.diary.data.models.DiaryCategory;

import java.util.Optional;

public interface DiaryService {

    Diary saveDiary(Diary newDiary);


    Diary findByDiaryCategory(DiaryCategory diaryCategory);

    Optional<Diary> findByDiaryId(String diaryId);
}
