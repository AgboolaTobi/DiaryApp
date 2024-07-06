package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Diary;
import com.diaryApp.diary.data.models.DiaryCategory;
import com.diaryApp.diary.data.repositories.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DiaryServiceApp implements DiaryService {


    private final DiaryRepository diaryRepository;


    @Override
    public Diary saveDiary(Diary newDiary) {
        return diaryRepository.save(newDiary);
    }

    @Override
    public Diary findByDiaryCategory(DiaryCategory diaryCategory) {
        return diaryRepository.findByDiaryCategory(diaryCategory);
    }

    @Override
    public Optional<Diary> findByDiaryId(String diaryId) {
        return diaryRepository.findById(Integer.valueOf(diaryId));
    }


}
