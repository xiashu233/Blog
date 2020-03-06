package com.xiashu.spblog.service;


import com.xiashu.spblog.bean.User;

public interface UserService {

    User checkUser(String userName, String password);
}
