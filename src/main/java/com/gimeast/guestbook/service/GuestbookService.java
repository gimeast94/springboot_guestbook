package com.gimeast.guestbook.service;

import com.gimeast.guestbook.data.dto.GuestbookDto;
import com.gimeast.guestbook.data.entity.Guestbook;

public interface GuestbookService {

    default Guestbook dtoToEntity(GuestbookDto guestbookDto) {
        Guestbook guestBook = Guestbook.builder()
                .gno(guestbookDto.getGno())
                .title(guestbookDto.getTitle())
                .content(guestbookDto.getContent())
                .writer(guestbookDto.getWriter())
                .build();
        return guestBook;
    }

    Long register(GuestbookDto guestbookDto);

}
