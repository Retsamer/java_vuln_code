package com.cinderella.demo.mapper;

import com.cinderella.demo.dao.Vuln;
import com.cinderella.demo.dao.VulnRepaire;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/7 11:25
 * @Description
 **/
@Mapper
public interface VulnRepaireMapper {
    VulnRepaire findRepaireBySymbol(@Param("str") String str);

    List<VulnRepaire> findURLRRepaireByUrl();

    List<VulnRepaire> findSSRFRepaireByUrl();

}
