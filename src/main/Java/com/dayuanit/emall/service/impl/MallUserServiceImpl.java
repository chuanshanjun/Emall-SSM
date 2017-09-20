package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallUserMapper;
import com.dayuanit.emall.pojo.MallUser;
import com.dayuanit.emall.service.MallUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallUserServiceImpl implements MallUserService{

    @Autowired
    private MallUserMapper mallUserMapper;

    @Override
    public void regist(String username, String password, String passwordOther, String email, String birthday, int cellphone, int sex) {
        if ("".equals(username) || null == username) {
            throw new EmallException("用户名不能为空");
        }

        if ("".equals(password) || null == password) {
            throw new EmallException("密码不能为空");
        }

        if ("".equals(passwordOther) || null == passwordOther) {
            throw new EmallException("确认密码不能为空");
        }

        if (!password.equals(passwordOther)) {
            throw new EmallException("密码不一致");
        }

        MallUser mallUser = mallUserMapper.getMallUserByUserName(username);

        if (null != mallUser) {
            throw new EmallException("用户名已经存在");
        }

        password += username;//加盐操作
        password = DigestUtils.md5Hex(password);//MD5加签

        mallUser = new MallUser();
        mallUser.setUsername(username);
        mallUser.setPassword(password);
        mallUser.setCellphone(cellphone);
        mallUser.setEmail(email);
        mallUser.setBirthday(birthday);
        mallUser.setSex((byte)sex);
        mallUser.setStatus((byte)0);

        int rows = mallUserMapper.addMallUser(mallUser);

        if (1 != rows) {
            throw new EmallException("开户失败");
        }
    }

    @Override
    public MallUser login(String username, String password) {
        if ("".equals(username) || null == username) {
            throw new EmallException("用户名不能为空");
        }

        if ("".equals(password) || null == password) {
            throw new EmallException("密码不能为空");
        }

        MallUser mallUser = mallUserMapper.getMallUserByUserName(username);
        if (null == mallUser) {
            throw new EmallException("用户不存在");
        }

        password += username;
        password = DigestUtils.md5Hex(password);
        if (!password.equals(mallUser.getPassword())) {
            throw new EmallException("密码不正确");
        }

        return mallUser;
    }
}
