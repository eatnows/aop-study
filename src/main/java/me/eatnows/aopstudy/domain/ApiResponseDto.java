package me.eatnows.aopstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int statusCode;
    private T data;

    public ApiResponseDto(int statusCode) {
        this.statusCode = statusCode;
    }
}
