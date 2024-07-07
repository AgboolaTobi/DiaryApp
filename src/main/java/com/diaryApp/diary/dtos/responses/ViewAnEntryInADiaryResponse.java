package com.diaryApp.diary.dtos.responses;

import com.diaryApp.diary.data.models.Entry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ViewAnEntryInADiaryResponse {
    private Entry targetEntry;
}
