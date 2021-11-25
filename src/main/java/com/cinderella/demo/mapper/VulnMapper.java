package com.cinderella.demo.mapper;

import com.cinderella.demo.dao.Vuln;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/3 10:22
 * @Description
 **/
@Mapper
public interface VulnMapper {
    List<Vuln> findAllVuln();
}
