package com.portal.pageModel;

import java.util.Date;

/**
 * Created by FGZ on 2017/12/4.
 */
public class UserModuleOrderPage {
    private static final long serialVersionUID = 1909878082948146724L;

    private Integer staffId;
    private Integer roleId;
    private Integer id;
    private Integer userId;
    private String userName;
    private String mobile;  //用户手机号
    private Integer moduleId;  //模块名称的id
    private String moduleName; //模块名称
    private Integer opeUserId;  //操作人员id
    private String opeUserName;
    private Float amount;  //总金额
    private String expiredate; //截止日期
    private String description;  //描述
    private Integer duration;  //延长的时长
    private Integer unit;  //延长的时长的单位
    private Date time;  //延长到的时间

    public UserModuleOrderPage() {
    }

    public UserModuleOrderPage(Integer staffId, Integer roleId, Integer id, Integer userId, String userName, String mobile, Integer moduleId, String moduleName, Integer opeUserId, String opeUserName, Float amount, String expiredate, String description, Integer duration, Integer unit, Date time) {
        this.staffId = staffId;
        this.roleId = roleId;
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.opeUserId = opeUserId;
        this.opeUserName = opeUserName;
        this.amount = amount;
        this.expiredate = expiredate;
        this.description = description;
        this.duration = duration;
        this.unit = unit;
        this.time = time;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getOpeUserId() {
        return opeUserId;
    }

    public void setOpeUserId(Integer opeUserId) {
        this.opeUserId = opeUserId;
    }

    public String getOpeUserName() {
        return opeUserName;
    }

    public void setOpeUserName(String opeUserName) {
        this.opeUserName = opeUserName;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
