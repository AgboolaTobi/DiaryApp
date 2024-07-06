package com.diaryApp.diary.data.repositories;

import com.diaryApp.diary.data.models.Diary;
import com.diaryApp.diary.data.models.DiaryCategory;
import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {
    Diary findByDiaryCategory(DiaryCategory category);
}
