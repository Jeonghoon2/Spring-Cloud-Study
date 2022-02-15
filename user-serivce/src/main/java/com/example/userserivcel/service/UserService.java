package com.example.userserivcel.service;

import com.example.userserivcel.dto.OrderDto;
import com.example.userserivcel.dto.UserDto;
import com.example.userserivcel.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    final UserMapper userMapper;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    //Now time
    LocalDate now = LocalDate.now();

    public void createUser(UserDto userDto) {
        userDto.setCreateAt(now);
        userDto.setUserid(UUID.randomUUID().toString());
        userDto.setPwd(passwordEncoder.encode(userDto.getPwd()));
        userMapper.createUser(userDto);
    }

    public UserDto getUserById(String userid) {
    UserDto userDto = userMapper.getUserById(userid);
        if (userDto == null){
            throw new UsernameNotFoundException("User not found");
        }

        List<OrderDto> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    public Iterable<UserDto> userByAll(){
        return userMapper.getUserByAll();
    }

    public List<OrderDto> order(OrderDto orderDto){
        return null;
    }
}

