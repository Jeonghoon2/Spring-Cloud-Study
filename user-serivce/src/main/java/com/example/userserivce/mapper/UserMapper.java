package com.example.userserivce.mapper;

import com.example.userserivce.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    //유저 회원가입
    void createUser(UserDto userDto);

    //특정 유저 불러오기
    UserDto getUserById(String userId);

    //전체 유저 불러오기
    List<UserDto> getUserByAll();

    UserDto findByEmail(String email);

    UserDto getUserDetailByEmail(String email);
}
