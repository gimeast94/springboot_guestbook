package com.gimeast.guestbook.data.dto;

import lombok.Data;

@Data
public class SearchStatus {
    private String title;
    private String content;

    public SearchStatus(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
