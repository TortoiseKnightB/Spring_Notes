package com.knight.security.dao;

import com.knight.security.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author TortoiseKnightB
 * @date 2022/08/15
 */
@Mapper
public interface UsersMapper {

    Users queryUser(@Param("username") String username);
}
