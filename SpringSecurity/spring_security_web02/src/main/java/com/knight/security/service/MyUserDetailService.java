package com.knight.security.service;

import com.knight.security.dao.UsersMapper;
import com.knight.security.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TortoiseKnightB
 * @date 2022/08/15
 */
@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询密码（数据库传功来的是加密的密码）。这里方便测试，手动对密码进行加密
        Users users = usersMapper.queryUser(username);

        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admins");    // 设置用户权限(admins、manager、ROLE_sale、ROLE_admin)
        User user = new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), authorities);
        return user;
    }
}
