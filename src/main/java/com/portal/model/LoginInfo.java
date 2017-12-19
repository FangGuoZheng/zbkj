package com.portal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by FGZ on 2017/12/6.
 */
@Entity
@Table(name = "m4_login_info")
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1908578952948146722L;

    private Integer id;
    private String userId;
    private Date loginTime;
    private String loginIP;
    private Date logoutTime;

    public LoginInfo() {
    }

    public LoginInfo(Integer id, String userId, Date loginTime, String loginIP, Date logoutTime) {
        this.id = id;
        this.userId = userId;
        this.loginTime = loginTime;
        this.loginIP = loginIP;
        this.logoutTime = logoutTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "logintime")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "loginip")
    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    @Column(name = "logouttime")
    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}
