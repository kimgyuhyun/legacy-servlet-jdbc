package com.legacy.practice.step3.service;

import com.legacy.practice.step3.dao.UserDao;
import com.legacy.practice.step3.dto.UserCreateWithDetailRequest;
import com.legacy.practice.step3.dto.UserDto;
import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.exception.UserDetailNotFoundException;
import com.legacy.practice.step3.exception.UserNotFoundException;
import com.legacy.practice.step3.repository.UserDetailRepository;
import com.legacy.practice.step3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public User loadUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> loadAllUserList() {
        return userRepository.findAll();
    }

    public User loadUserWithDetailById(long id) {
        User user = userRepository.findUserWithDetailById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (user.getDetail() == null) {
            throw new UserDetailNotFoundException(id);
        }

        return user;
    }

    public List<User> loadAllUserWithDetailList() {
        return userRepository.findAllUserWithDetailList();
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
