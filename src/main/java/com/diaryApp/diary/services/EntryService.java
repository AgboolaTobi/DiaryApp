package com.diaryApp.diary.services;

import com.diaryApp.diary.data.models.Entry;



public interface EntryService {
    void saveEntry(Entry entries);

    Entry findEntryById(String entryId);

    void deleteEntry(Entry targetEntry);
}
