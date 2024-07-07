package com.diaryApp.diary.dtos.responses;

import com.diaryApp.diary.data.models.Entry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ViewAllDiaryEntriesResponse {
    private String message;
    private List<Entry> diaryEntries;
}
