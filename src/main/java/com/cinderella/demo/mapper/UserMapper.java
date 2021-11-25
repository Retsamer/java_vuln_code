package com.cinderella.demo.mapper;

import com.cinderella.demo.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cinderella
 * @time 2021/8/24 14:35
 * @Description
 **/
@Mapper
public interface UserMapper {
    List<User> findByNameVuln(@Param("name") String name);

    List<User> likeFindByNameVuln(@Param("name") String name);

    List<User> orderByAgeVuln(@Param("order") String order);

    User findByNameSec(@Param("name") String name);

    List<User> likeFindByNameSec(@Param("name") String name);

    User findById(@Param("id") Integer id);

    void updateContent(@Param("username") String username,@Param("content") String content);

    String findContentByUsername(@Param("username") String username);
}
