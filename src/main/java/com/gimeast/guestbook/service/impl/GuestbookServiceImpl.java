package com.gimeast.guestbook.service.impl;

import com.gimeast.guestbook.data.dto.GuestbookDto;
import com.gimeast.guestbook.data.entity.Guestbook;
import com.gimeast.guestbook.repository.GuestbookRepository;
import com.gimeast.guestbook.service.GuestbookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    public GuestbookServiceImpl(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    @Override
    public Long register(GuestbookDto guestbookDto) {

        Guestbook guestBook = dtoToEntity(guestbookDto);
        log.info("dto to entity : " + guestBook);

        guestbookRepository.save(guestBook);

        return guestBook.getGno();
    }







}
