package com.diaryApp.diary.data.repositories;

import com.diaryApp.diary.data.models.Diary;
import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {
}
