package com.cinderella.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Cinderella
 * @time 2021/9/1 15:38
 * @Description
 **/
@Data
public class ResponseCode implements Serializable {
    private Integer code;
    private String message;

    private ResponseCode(StatusEnums responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    private ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回成功信息
     * @return
     */
    public static ResponseCode success() {
        return new ResponseCode(StatusEnums.SUCCESS);
    }

    /**
     * 返回错误信息
     * @param statusEnums      响应码
     * @return
     */
    public static ResponseCode error(StatusEnums statusEnums) {
        return new ResponseCode(statusEnums);
    }
}
