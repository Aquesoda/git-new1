package com.trainning.mysites.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(length = 30,unique = true)
    @NotNull
    private String account;
    @Column(length = 30)
    @NotNull
    private String password;
    @Column(length = 30)
    private String name;
    //枚举的使用
    public enum Sex{
      男,女;//枚举中常量结束位置要有分号
        //将枚举数据转换成了一个列表
        public static List<String> toList(){
            Sex[] sex = Sex.values();
            List<String> datas = new ArrayList<>();
            for (Sex s : sex) {
                datas.add(s.name());
            }
            return datas;
        }
    };
    private Sex grander;

    private LocalDate birthday;
    @Column(length = 11)
    private String mobile;
    @Column(length = 100)
    private String email;

    private Integer lasttime;   //最后登陆时间  处理整形日期

    private Integer logincount; //登录次数

    private Integer vaildstate = 1; //用户是否有效 0表示无效，1表示有效

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getGrander() {
        return grander;
    }

    public void setGrander(Sex grander) {
        this.grander = grander;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLasttime() {
        return lasttime;
    }

    public void setLasttime(Integer lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public Integer getvaildstate() {
        return vaildstate;
    }

    public void setvaildstate(Integer vaildstate) {
        this.vaildstate = vaildstate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", grander=" + grander +
                ", birthday=" + birthday +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", lasttime=" + lasttime +
                ", logincount=" + logincount +
                ", vaildstate=" + vaildstate +
                '}';
    }
}
