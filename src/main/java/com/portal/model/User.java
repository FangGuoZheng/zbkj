package com.portal.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FGZ on 2017/10/26.
 */
@Entity
@Table(name = "m4_user")
public class User implements java.io.Serializable {

    private static final long serialVersionUID = 1908545072948146721L;

    // Fields
    private Integer id;
    private String uuid;
    private String name;
    private String nickname;
    private String areacode;
    private String mobile;
    private String password;
    private String avatar;  // 联系⼈头像链接
    private String email;
    private Integer gender;  // 联系⼈性别，-1：未设置，0：⼥，1：男，2：其它
    private String birthdate;
    private String location;
    private String type;  // 是会员还是普通用户
    private Date expiredate;
    private Staff sale;
    private Role role;
    private Date loginTime;
    private String loginIP;
    private boolean deleteflag;

    public User() {
        this.deleteflag = false;
    }

    public User(Integer id, String uuid, String name, String nickname, String areacode, String mobile, String password, String avatar, String email, Integer gender, String birthdate, String location, String type, Date expiredate, Staff sale, Role role, Date loginTime, String loginIP, boolean deleteflag) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.nickname = nickname;
        this.areacode = areacode;
        this.mobile = mobile;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.gender = gender;
        this.birthdate = birthdate;
        this.location = location;
        this.type = type;
        this.expiredate = expiredate;
        this.sale = sale;
        this.role = role;
        this.loginTime = loginTime;
        this.loginIP = loginIP;
        this.deleteflag = deleteflag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    @Column(name = "expireDate")
    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    @ManyToOne
    @JoinColumn(name = "sale_id")
    public Staff getSale() {
        return sale;
    }

    public void setSale(Staff sale) {
        this.sale = sale;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public boolean isDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(boolean deleteflag) {
        this.deleteflag = deleteflag;
    }
}
