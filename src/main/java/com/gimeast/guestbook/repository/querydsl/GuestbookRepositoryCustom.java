package com.gimeast.guestbook.repository.querydsl;

import com.gimeast.guestbook.data.entity.GuestBook;
import com.gimeast.guestbook.data.dto.SearchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GuestbookRepositoryCustom {

    Page<GuestBook> findBySearch(SearchStatus status, Pageable pageable);

}
