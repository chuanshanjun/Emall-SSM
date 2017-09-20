package com.dayuanit.emall.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 使用了hibernate.validator验证
 */
public class UserVo {

    @NotNull
    @Length(min = 3, max = 10, message = ("用户名长度不符合"))
    private String username;

    @NotNull
    @Length(min = 3, max = 10, message = ("密码长度不符合"))
    private String password;

    @NotNull
    @Length(min = 3, max = 10, message = ("确认密码长度不符合"))
    private String passwordOther;

    @NotNull
    @Email(message = "邮箱格式不正确")
    private String email;

    private String cellPhone;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordOther() {
        return passwordOther;
    }

    public void setPasswordOther(String passwordOther) {
        this.passwordOther = passwordOther;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
