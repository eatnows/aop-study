package me.eatnows.aopstudy.domain;

import lombok.Data;

@Data
public class JoinReqDto {
    private String username;
    private String password;
    private String phone;
}
