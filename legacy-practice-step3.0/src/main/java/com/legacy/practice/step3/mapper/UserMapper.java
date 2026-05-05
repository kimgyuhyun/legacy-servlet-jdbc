package com.legacy.practice.step3.mapper;

import com.legacy.practice.step3.dto.UserDetailDto;
import com.legacy.practice.step3.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<UserDto> findAll();
    UserDto findById(@Param("id") Long id);
    int insert(UserDto dto);
    int insertUserDetail(UserDetailDto dto);
    int updateById(UserDto dto);
    int updateNameById(@Param("id") Long id, @Param("name") String name);
    List<UserDto> findByNameAndAddress(
            @Param("name")String name, @Param("address") String address);
    List<UserDto> findDynamicByNameAndAddress(
            @Param("name")String name, @Param("address") String address);
    UserDetailDto findDetailByUserId(@Param("userId") Long id);
    int deleteById(@Param("id") Long id);
    int updateDynamicNameAddressById(UserDto dto);
    List<UserDto> findUserByIdList(@Param("idList") List<Long> idList);
    int deleteByIdList(@Param("idList") List<Long> idList);

    long contAll();
    List<UserDto> findAllPaged(@Param("offset") int offset, @Param("limit") int limit);
}
