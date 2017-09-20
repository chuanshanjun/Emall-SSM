package com.dayuanit.emall.controller;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallUser;

import javax.crypto.ExemptionMechanismException;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    protected static final String LOGIN_USER = "login_user_flag";

    protected int getUserId(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (null == session) {
            throw new EmallException("用户未登陆");
        }

        Object object = session.getAttribute(LOGIN_USER);

        if (null == object) {
            throw new EmallException("用户未登录");
        }

        MallUser MallUser = (MallUser)object;

        return MallUser.getId();
    }

}
