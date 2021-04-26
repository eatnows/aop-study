package me.eatnows.aopstudy.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateReqDto {
    @NotBlank(message = "password를 입력하지 않았습니다.")
    private String password;
    private String phone;
}
