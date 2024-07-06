package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Entry;
import com.diaryApp.diary.data.repositories.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntryServiceApp implements EntryService{
    private final EntryRepository entryRepository;

    @Override
    public void saveEntry(Entry entry) {
        entryRepository.save(entry);
    }
}
