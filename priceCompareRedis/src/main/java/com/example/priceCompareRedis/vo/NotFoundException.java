package com.example.priceCompareRedis.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Keys in Redis")
public class NotFoundException extends RuntimeException {

    private String errmsg;
    private HttpStatus httpStatus;

    public NotFoundException(String errmsg, HttpStatus httpStatus) {
        this.errmsg=errmsg;
        this.httpStatus = httpStatus;
    }

}