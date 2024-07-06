package com.diaryApp.diary.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Setter
@Getter
@ToString
public class Diary {
    @Id
    private String id;
    private String userId;
    private String diaryName;
    private String diaryDescription;
    private DiaryCategory diaryCategory;
    private List<Entry> entries;
    private LocalDateTime createdAt;


}
