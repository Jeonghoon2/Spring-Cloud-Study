package com.example.userserivce.dto;

import com.example.userserivce.vo.OrderVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private LocalDate createAt;
    private String userId;

    private List<OrderVo> orders;

}
