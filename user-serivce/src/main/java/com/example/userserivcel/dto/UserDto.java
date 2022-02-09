package com.example.userserivcel.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private String email;

    private String name;

    private String pwd;

    private String userid;

    private LocalDate createAt;

}
