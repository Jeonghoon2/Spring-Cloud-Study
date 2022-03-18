package com.example.userserivce.service;

import com.example.userserivce.client.OrderServiceClient;
import com.example.userserivce.dto.OrderDto;
import com.example.userserivce.dto.UserDto;
import com.example.userserivce.mapper.UserMapper;
import com.example.userserivce.vo.OrderVo;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    final UserMapper userMapper;
    final BCryptPasswordEncoder passwordEncoder;
    final Environment env;
    final RestTemplate restTemplate;
    final OrderServiceClient orderServiceClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userMapper.findByEmail(email);

        if (userDto ==null)
            throw new UsernameNotFoundException(email);

        return new User(userDto.getEmail(),userDto.getPwd(),
                true,true,true,true,
                new ArrayList<>());
    }

    @Autowired
    public UserService(UserMapper userMapper,
                       BCryptPasswordEncoder passwordEncoder,
                       Environment env,
                       RestTemplate restTemplate,
                       OrderServiceClient orderServiceClient) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
        this. restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
    }

    //Now time
    LocalDate now = LocalDate.now();

    public void createUser(UserDto userDto) {
        userDto.setCreateAt(now);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setPwd(passwordEncoder.encode(userDto.getPwd()));
        userMapper.createUser(userDto);
    }

    public UserDto getUserById(String userid) {
        UserDto userDto = userMapper.getUserById(userid);
        if (userDto == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<OrderVo> ordersList = null;

        /* Feign Client*/
        /* Feign Eception handling*/
        /*try {
            ordersList = orderServiceClient.getOrders(userDto.getEmail());
        }catch (FeignException ex){
            log.error(ex.getMessage());
        }*/
        /*GETERRORDECODER*/
        ordersList = orderServiceClient.getOrders(userDto.getEmail());
        userDto.setOrders(ordersList);
        return userDto;



        /*
        String orderUrl = String.format(env.getProperty("order_service.url"),userDto.getEmail());
        ResponseEntity<List<OrderVo>> orderListResponse = restTemplate.exchange(
                orderUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
        });
        List<OrderVo> ordersList = orderListResponse.getBody();*/
        /* Using as rest Template */

    }

    public Iterable<UserDto> userByAll(){
        return userMapper.getUserByAll();
    }

    public List<OrderDto> order(OrderDto orderDto){
        return null;
    }

    public UserDto getUserDetailByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}

