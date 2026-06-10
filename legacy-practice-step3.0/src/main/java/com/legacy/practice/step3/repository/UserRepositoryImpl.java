package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.dto.QUserWithDetailResponse;
import com.legacy.practice.step3.dto.UserResponse;
import com.legacy.practice.step3.dto.UserWithDetailResponse;
import com.legacy.practice.step3.entity.QUser;
import com.legacy.practice.step3.entity.QUserDetail;
import com.legacy.practice.step3.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<UserResponse> findAllUserResponseByProjection() {
        QUser user = QUser.user;

        return queryFactory
                .select(Projections.constructor(UserResponse.class,
                        user.id,
                        user.name,
                        user.age,
                        user.birthDate,
                        user.address,
                        user.createAt,
                        user.updateAt
                ))
                .from(user)
                .fetch();
    }

    @Override
    public List<UserWithDetailResponse> findAllUserWithDetailByQueryProjection() {
        QUser user = QUser.user;
        QUserDetail userDetail = QUserDetail.userDetail;

        return queryFactory
                .select(new QUserWithDetailResponse(
                        user.id,
                        user.name,
                        user.age,
                        user.birthDate,
                        user.address,
                        userDetail.phone,
                        userDetail.job,
                        user.createAt,
                        user.updateAt

                ))
                .from(user)
                .leftJoin(user.detail, userDetail)
                .fetch();
    }

    @Override
    public List<UserResponse> findPagedUserResponse(Pageable pageable) {
        QUser user = QUser.user;

        return queryFactory
                .select(Projections.constructor(UserResponse.class,
                        user.id,
                        user.name,
                        user.age,
                        user.birthDate,
                        user.address,
                        user.createAt,
                        user.updateAt
                ))
                .from(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(user.id.asc())
                .fetch();
    }

    @Override
    public long countAllUsers() {
        QUser user = QUser.user;

        Long result = queryFactory
                .select(user.count())
                .from(user)
                .fetchOne();

        return result != null ? result : 0L;
    }


}
