package com.trainning.mysites.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//登录的实体类
public class UserLogin {

    @NotNull(message = "账号不能为空")
    @Size(max = 12,message = "账号至少6个字符")
    private String account;

    @NotNull(message = "密码不能为空")
    @Size(max = 12,message = "密码至少6个字符")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
