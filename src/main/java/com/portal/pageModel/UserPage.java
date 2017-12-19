package com.portal.pageModel;

import java.util.Date;

/**
 * Created by FGZ on 2017/11/1.
 */
public class UserPage {

    private Integer id;
    private String uuid;
    private String name;
    private String nickname;
    private String mobile;
    private String password;
    private String email;
    private Integer gender;
    private String birthdate;
    private String location;
    private String type;
    private String areacode;
    private String avatar;
    private Integer saleId;
    private Integer roleId;
    private Date loginTime;
    private String loginIP;
    private int pageSize;
    private int page;

    public UserPage() {
        this.pageSize=50;
        this.page=1;
    }

    public UserPage(Integer id, String uuid, String name, String nickname, String mobile, String password, String email, Integer gender, String birthdate, String location, String type, String areacode, String avatar, Integer saleId, Integer roleId, Date loginTime, String loginIP, int pageSize, int page) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.nickname = nickname;
        this.mobile = mobile;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthdate = birthdate;
        this.location = location;
        this.type = type;
        this.areacode = areacode;
        this.avatar = avatar;
        this.saleId = saleId;
        this.roleId = roleId;
        this.loginTime = loginTime;
        this.loginIP = loginIP;
        this.pageSize = pageSize;
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
