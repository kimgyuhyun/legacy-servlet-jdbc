package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.entity.QUser;
import com.legacy.practice.step3.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<User> findAllByQueryDSL() {
        QUser user = QUser.user;

        return queryFactory
                .selectFrom(user)
                .fetch();
    }

}
