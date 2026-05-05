package com.legacy.practice.step3.service;

import com.legacy.practice.step3.dao.UserDao;
import com.legacy.practice.step3.dto.UserCreateWithDetailRequest;
import com.legacy.practice.step3.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createUserWithDetail(UserCreateWithDetailRequest req) {
//        boolean rollbackTest= true;

        UserDto user = req.toUserDto();
        userDao.insert(user);
//        if (rollbackTest) {
//            throw new RuntimeException("rollback test");
//        }

        userDao.insertUserDetail(req.toUserDetailDto(user.getId()));}

}
