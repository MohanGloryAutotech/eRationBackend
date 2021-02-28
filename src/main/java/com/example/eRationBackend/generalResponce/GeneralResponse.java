package com.example.eRationBackend.generalResponce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class GeneralResponse<T> {

    private T data;
    private String msg;
    private Boolean success;
    private Long timeStamp;
    private Integer statusCode;



    public GeneralResponse(T data, String msg, Boolean success, long currentTimeMillis, HttpStatus statusCode) {
        this.data = data;
        this.msg = msg;
        this.success = success;
        this.timeStamp = currentTimeMillis;
        this.statusCode = statusCode.value();
    }
}