package com.cinderella.demo.mapper;

import com.cinderella.demo.dao.Vuln;
import com.cinderella.demo.dao.VulnPath;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/7 11:09
 * @Description
 **/
@Mapper
public interface VulnPathMapper {
    VulnPath findVuln(@Param("vuln") String vuln);

    /**
     * SQL注入查询
     */
    List<VulnPath> findAllSQLVuln01();

    /**
     * XSS查询
     */
    List<VulnPath> findAllXSSVuln01();

    /**
     * URL重定向查询
     */
    List<VulnPath> findAllURLVuln01();

    /**
     * Thymeleaf模板注入查询
     */

    List<VulnPath> findAllThymeleafVuln01();

    /**
     * XXE漏洞
     */
    List<VulnPath> findAllXXEVuln01();

    /**
     * SSRF漏洞
     */
    List<VulnPath> findAllSSRFVuln01();

    /**
     * 路径穿越漏洞
     */
    List<VulnPath> findAllPathtraversalVuln01();

    /**
     * idor漏洞
     */
    List<VulnPath> findAllIdorVuln01();

    /**
     *文件上传漏洞
     */
    List<VulnPath> findAllUploadVuln01();

    /**
     * Fastjson反序列化漏洞
     */

    List<VulnPath> findAllFastjsonVuln01();

    /**
     * 命令执行漏洞
     */
    List<VulnPath> findAllCmdiVuln01();

    /**
     * Shiro反序列化漏洞
     */
    List<VulnPath> findAllShiroVuln01();
}
