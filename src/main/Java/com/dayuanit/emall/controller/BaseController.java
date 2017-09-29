package com.dayuanit.emall.controller;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallUser;
import com.mysql.jdbc.Driver;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.crypto.ExemptionMechanismException;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseController {

    protected static final String LOGIN_USER = "login_user_flag";

//    protected int getUserId(HttpServletRequest req) {
//        HttpSession session = req.getSession(false);
//        if (null == session) {
//            throw new EmallException("用户未登陆");
//        }
//
//        Object object = session.getAttribute(LOGIN_USER);
//
//        if (null == object) {
//            throw new EmallException("用户未登录");
//        }
//
//        MallUser MallUser = (MallUser)object;
//
//        return MallUser.getId();
//
//    }

    protected void setCurrentUser(MallUser mallUser) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(LOGIN_USER, mallUser);
    }

    protected Integer getUserId(HttpServletRequest req) {
        Subject subject = SecurityUtils.getSubject();
        Object object= subject.getSession().getAttribute(LOGIN_USER);
        if (null == object) {
            throw new EmallException("请先登陆");
        }

        MallUser mallUser = (MallUser)object;

        return mallUser.getId();
    }

}
