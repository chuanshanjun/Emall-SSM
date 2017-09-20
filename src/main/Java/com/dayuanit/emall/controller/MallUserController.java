package com.dayuanit.emall.controller;

import com.dayuanit.emall.dto.AjaxResultDTO;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallUser;
import com.dayuanit.emall.service.MallUserService;
import com.dayuanit.emall.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/malluser")
public class MallUserController extends BaseController{

    Logger log = LoggerFactory.getLogger(MallUserController.class);

    @Autowired
    private MallUserService mallUserService;

    @RequestMapping("/toRegister")
    public String toRegist() {
        return "register";
    }

    @RequestMapping("/register")
    @ResponseBody
    public AjaxResultDTO register(@Validated UserVo userVo, BindingResult br, HttpSession session) {
        if (br.hasErrors()) {
            String msg = "";
            List<FieldError> list = br.getFieldErrors();//获取所有的错误信息
            for (FieldError fieldError : list) {
                msg += fieldError.getDefaultMessage() + ",";
            }

            return AjaxResultDTO.failed(msg);
        }

        try {
            String code = (String) session.getAttribute("code");
            if (!userVo.getCode().equals(code)) {
                session.removeAttribute("code");//验证码应该及时更改

                return AjaxResultDTO.failed("验证码错误");
            }

            mallUserService.regist(userVo.getUsername(), userVo.getPassword(),
                    userVo.getPasswordOther(), userVo.getEmail(), "1990",
                    Integer.parseInt(userVo.getCellPhone()),
                    1);
        } catch (EmallException ee) {
            log.error("注册失败：{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("注册异常：{}", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }

        return AjaxResultDTO.success();
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResultDTO login(String username, String password, HttpServletRequest req) {
        try {
            MallUser mallUser = mallUserService.login(username, password);
            req.getSession().setAttribute(LOGIN_USER, mallUser);//将用户名放到session中
        } catch (EmallException ee) {
            log.error("登陆失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("登陆异常");
            return AjaxResultDTO.failed("系统异常请联系客服");
        }

        return AjaxResultDTO.success();
    }
}
