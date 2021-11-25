package com.cinderella.demo.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/7 11:07
 * @Description
 **/
@Data
public class VulnPath implements Serializable {
    private int id;
    private String symbol;
    private String name;
    private String content;
    private String url;
    private String url1;
    private String color;
    private List<VulnRepaire> vulnRepaire;

}
