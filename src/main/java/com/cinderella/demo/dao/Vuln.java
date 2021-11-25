package com.cinderella.demo.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cinderella
 * @time 2021/9/3 10:05
 * @Description
 **/
@Data
public class Vuln implements Serializable {
    private int id;
    private String name;
    private String content;
    private String url;
    private String color;
}
