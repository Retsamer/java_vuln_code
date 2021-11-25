package com.cinderella.demo.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cinderella
 * @time 2021/9/7 11:24
 * @Description
 **/
@Data
public class VulnRepaire implements Serializable {
    private int id;
    private String symbol;
    private String name;
    private String content;
    private String url;
}
