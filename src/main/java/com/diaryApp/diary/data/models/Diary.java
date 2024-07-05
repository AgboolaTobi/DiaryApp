package com.diaryApp.diary.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Setter
@Getter
public class Diary {
    @Id
    private String id;
    private String diaryName;
    private String diaryDescription;
    private DiaryCategory diaryCategory;
    private List<Entry> entries;

}
