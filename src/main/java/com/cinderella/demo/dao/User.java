package com.cinderella.demo.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cinderella
 * @time 2021/8/24 14:19
 * @Description
 **/
@Data
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String role;
    private String phone;
    private String content;
}
