package com.diaryApp.diary.dtos.responses;

import com.diaryApp.diary.data.models.Entry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ViewAllDiaryEntriesResponse {
    private String message;
    private List<Entry> diaryEntries;
}
