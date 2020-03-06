package com.xiashu.spblog.service.impl;


import com.xiashu.spblog.bean.User;
import com.xiashu.spblog.mapper.UserMapper;
import com.xiashu.spblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User sqlUser = new User();
        sqlUser.setUsername(username);
        sqlUser.setPassword(password);
        User user = userMapper.selectOne(sqlUser);
        return user;
    }
}
