package com.example.userserivcel.mapper;

import com.example.userserivcel.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void createUser(UserDto userDto);

}
