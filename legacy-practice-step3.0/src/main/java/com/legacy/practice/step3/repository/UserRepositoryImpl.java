package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.entity.QUser;
import com.legacy.practice.step3.entity.QUserDetail;
import com.legacy.practice.step3.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<User> findUserWithDetailByIdDSL(Long id) {
        QUser user = QUser.user;
        QUserDetail userDetail = QUserDetail.userDetail;

        User result = queryFactory
                .selectFrom(user)
                .leftJoin(user.detail, userDetail)
                .fetchJoin()
                .where(user.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public List<User> findAllUserWithDetailListDSL() {
        QUser user = QUser.user;
        QUserDetail userDetail = QUserDetail.userDetail;

        return queryFactory
                .selectFrom(user)
                .leftJoin(user.detail, userDetail)
                .fetchJoin()
                .distinct()
                .fetch();
    }

    @Override
    public List<User> searchUserList(String name, Integer age, String address) {
        QUser user = QUser.user;

        return queryFactory
                .selectFrom(user)
                .where(
                        nameContains(name),
                        ageEq(age),
                        addressContains(address)
                )
                .fetch();
    }

    private BooleanExpression nameContains(String name) {
        return name != null ? QUser.user.name.contains(name) : null;
    }

    private BooleanExpression ageEq(Integer age) {
        return age != null ? QUser.user.age.eq(age) : null;
    }

    private BooleanExpression addressContains(String address) {
        return address != null ? QUser.user.address.contains(address) : null;
    }

}
