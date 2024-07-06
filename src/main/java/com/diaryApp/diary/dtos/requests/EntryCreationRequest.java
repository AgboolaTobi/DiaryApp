package com.diaryApp.diary.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryCreationRequest {
    private String userId;
    private String diaryId;
    private String title;
    private String content;

}
