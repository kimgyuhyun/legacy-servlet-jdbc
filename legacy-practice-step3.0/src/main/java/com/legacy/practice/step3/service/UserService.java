package com.legacy.practice.step3.service;

import com.legacy.practice.step3.dao.UserDao;
import com.legacy.practice.step3.dto.UserCreateWithDetailRequest;
import com.legacy.practice.step3.dto.UserDto;
import com.legacy.practice.step3.dto.UserPatchRequest;
import com.legacy.practice.step3.dto.UserUpdateRequest;
import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.entity.UserDetail;
import com.legacy.practice.step3.exception.UserDetailNotFoundException;
import com.legacy.practice.step3.exception.UserNotFoundException;
import com.legacy.practice.step3.repository.UserDetailRepository;
import com.legacy.practice.step3.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public User insertUser(User user) {
        User saved = userRepository.save(user);
        entityManager.refresh(saved);
        return saved;
    }

    @Transactional
    public User insertUserWithDetail(UserCreateWithDetailRequest req) {
        User user = req.toEntity();
        UserDetail detail = req.toDetailEntity(user);
        user.setDetail(detail);
        User saved = userRepository.save(user);
        entityManager.refresh(saved);
        return saved;
    }

    @Transactional
    public User updateUser(Long id, UserUpdateRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.updateProfile(req.getName(), req.getAge(), req.getBirthDate(), req.getAddress());
        userRepository.save(user);

        return user;
    }

    @Transactional
    public User patchUpdateuser(Long id, UserPatchRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.patchProfile(req.getName(), req.getAge(), req.getBirthDate(), req.getAddress());
        userRepository.save(user);

        return user;
    }


    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(user.getId());
    }


    public List<User> DslloadAllUserList() {
        return userRepository.findAllByQueryDSL();
    }


    public User loadUserWithDetailByIdDSL(long id) {
        User user = userRepository.findUserWithDetailByIdDSL(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (user.getDetail() == null) {
            throw new UserDetailNotFoundException(id);
        }

        return user;
    }

    public List<User> loadAllUserWithDetailListDSL() {
        return userRepository.findAllUserWithDetailListDSL();
    }






    @Transactional(rollbackFor = Exception.class)
    public void createUserWithDetail(UserCreateWithDetailRequest req) {
//        boolean rollbackTest= true;

        UserDto user = req.toUserDto();
        userDao.insert(user);
//        if (rollbackTest) {
//            throw new RuntimeException("rollback test");
//        }

        userDao.insertUserDetail(req.toUserDetailDto(user.getId()));
    }

}















