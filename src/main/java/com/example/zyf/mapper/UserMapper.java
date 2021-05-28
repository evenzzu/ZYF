/**
 * @projectName ZYF
 * @package com.example.zyf.mapper
 * @className com.example.zyf.mapper.UserMapper
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.zyf.model.User;

/**
 * UserMapper
 * @description
 * @author zyf
 * @date 2020/12/22 9:33
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    List<User> queryUser(@Param("id") String id);

    List<User> getUser();
}