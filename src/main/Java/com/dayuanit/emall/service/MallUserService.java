package com.dayuanit.emall.service;

import com.dayuanit.emall.pojo.MallUser;

public interface MallUserService {

    void regist(String username, String password, String passwordOther, String emal, String birthday, int cellphone,
                 int sex);

    MallUser login(String username, String password);

    MallUser getMallUser(String username);

}
