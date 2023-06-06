package com.gimeast.guestbook.repository.querydsl.impl;

import com.gimeast.guestbook.data.entity.Guestbook;
import com.gimeast.guestbook.data.dto.SearchStatus;
import com.gimeast.guestbook.data.entity.QGuestbook;
import com.gimeast.guestbook.repository.querydsl.GuestbookRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class GuestbookRepositoryCustomImpl extends QuerydslRepositorySupport implements GuestbookRepositoryCustom {

    public GuestbookRepositoryCustomImpl() {
        super(Guestbook.class);
    }


    @Override
    public Page<Guestbook> findBySearch(SearchStatus status, Pageable pageable) {
        QGuestbook qGuestbook = QGuestbook.guestbook;

        List<Guestbook> guestbookList = from(qGuestbook)
                .where(
                        !StringUtils.hasText(status.getTitle()) ? null : qGuestbook.title.likeIgnoreCase("%" + status.getTitle() + "%")
                                .or(
                                        !StringUtils.hasText(status.getContent()) ? null : qGuestbook.content.likeIgnoreCase("%" + status.getContent() + "%")
                                )

                )
                .offset(pageable.getOffset())// page 번호
                .limit(pageable.getPageSize())// page size
                .orderBy(qGuestbook.gno.desc())
                .fetch();

        Long count = from(qGuestbook)
                .select(qGuestbook.count())
                .where(
                        !StringUtils.hasText(status.getTitle()) ? null : qGuestbook.title.likeIgnoreCase("%" + status.getTitle() + "%"),
                        !StringUtils.hasText(status.getContent()) ? null : qGuestbook.content.likeIgnoreCase("%" + status.getContent() + "%")
                )
                .orderBy(qGuestbook.gno.desc())
                .fetchOne();

        return new PageImpl<>(guestbookList, pageable, count);
    }
}
