package com.portal.action;

import com.opensymphony.xwork2.ModelDriven;
import com.portal.model.Module;
import com.portal.model.ModuleAccess;
import com.portal.model.User;
import com.portal.pageModel.Json;
import com.portal.pageModel.UserModuleOrderPage;
import com.portal.service.*;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by FGZ on 2017/12/4.
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "UserModuleOrderAction")
public class UserModuleOrderAction extends BaseAction implements ModelDriven<UserModuleOrderPage> {
    private static final long serialVersionUID = 4426765445782995839L;
    private static final Logger logger = Logger.getLogger(UserModuleOrderAction.class);

    private UserModuleOrderServiceI userModuleOrderService;
    @Autowired
    public void setUserModuleOrderService(UserModuleOrderServiceI userModuleOrderService) {
        this.userModuleOrderService = userModuleOrderService;
    }

    private DictionaryServiceI dictionaryService;
    @Autowired
    public void setDictionaryService(DictionaryServiceI dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    private ModuleServiceI moduleService;
    @Autowired
    public void setModuleService(ModuleServiceI moduleService) {
        this.moduleService = moduleService;
    }

    private ModuleAccessServiceI moduleAccessService;
    @Autowired
    public void setModuleAccessService(ModuleAccessServiceI moduleAccessService) {
        this.moduleAccessService = moduleAccessService;
    }

    private UserServiceI userService;
    @Autowired
    public void setUserService(UserServiceI userService) {
        this.userService = userService;
    }

    UserModuleOrderPage userModuleOrderPage = new UserModuleOrderPage();
    @Override
    public UserModuleOrderPage getModel() {
        return userModuleOrderPage;
    }

    /**
     * 获取用户模块订单信息
     */
    public void get(){
        if (userModuleOrderPage.getStaffId() == 1){
            userModuleOrderPage.setStaffId(null);
        }
        List<UserModuleOrderPage> list = this.userModuleOrderService.get(userModuleOrderPage.getStaffId(), userModuleOrderPage.getUserName(), userModuleOrderPage.getModuleName(), userModuleOrderPage.getOpeUserId());
        Json j = new Json();
        if (list != null && list.size() != 0){
            logger.info("查询用户模块订单信息成功");
            j.setSuccess(true);
            j.setMsg("查询用户模块订单信息成功");
            j.setObj(list);
        } else{
            logger.info("查询用户模块订单信息失败");
            j.setSuccess(false);
            j.setMsg("查询用户模块订单信息失败");
            j.setObj(null);
        }
        super.writeJson(j);
    }

    /**
     * 新增用户模块延期订单
     */
    public void add(){
        if (!checkUserModuleOrderPageMessage(userModuleOrderPage)){
            return;
        }

        Json j = new Json();
        ModuleAccess moduleAccess = this.moduleAccessService.getByUserIdAndModuleId(userModuleOrderPage.getUserId(), userModuleOrderPage.getModuleId());

        Date expireDate = null;
        Date expireDateLast = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        if (moduleAccess != null){
            if (moduleAccess.getExpireDate() == null){
                expireDate = new Date();
                expireDateLast = expireDate;
                cal.setTime(expireDate);
            }else {
                expireDate = moduleAccess.getExpireDate();
                expireDateLast = expireDate;
                cal.setTime(expireDate);
            }
        }else {
            expireDate = new Date();
            expireDateLast = expireDate;
            cal.setTime(expireDate);
        }

        if (userModuleOrderPage.getUnit() != null) {
            if (userModuleOrderPage.getDuration() != null){
                if (userModuleOrderPage.getUnit() == 1) {
                    cal.add(Calendar.YEAR, userModuleOrderPage.getDuration());
                }else if (userModuleOrderPage.getUnit() == 2){
                    cal.add(Calendar.MONTH, userModuleOrderPage.getDuration());
                }else if (userModuleOrderPage.getUnit() == 3){
                    cal.add(Calendar.DAY_OF_MONTH, userModuleOrderPage.getDuration());
                }
                expireDateLast = cal.getTime();
            }else {
                logger.info("你填入的延长时长的值为空，请重新确认");
                j.setSuccess(false);
                j.setMsg("你填入的延长时长的值为空，请重新确认");
                j.setObj(null);
                super.writeJson(j);
                return ;
            }
        } else if (userModuleOrderPage.getTime() != null){
            if (userModuleOrderPage.getTime().getTime() <= expireDate.getTime()){
                logger.info("你输入的延长日期比截止日期还要早，请确认后再输入");
                j.setSuccess(false);
                j.setMsg("你输入的延长日期比截止日期还要早，请确认后再输入");
                j.setObj(null);
                super.writeJson(j);
                return ;
            } else{
                expireDateLast = userModuleOrderPage.getTime();
            }
        }
        userModuleOrderPage.setTime(expireDateLast);

        if (moduleAccess != null){
            moduleAccess.setExpireDate(expireDateLast);
            boolean maFlag = this.moduleAccessService.updateExpireDateByUserIdAndModuleId(moduleAccess);
            if (maFlag){
                logger.info("模块访问记录更新成功");
            }else{
                logger.info("模块访问记录更新失败");
            }
        }else{
            ModuleAccess moduleAccessTemp = new ModuleAccess();

            User user = this.userService.getByMobile(userModuleOrderPage.getMobile());
            moduleAccessTemp.setUser(user);
            Module module = this.moduleService.get(userModuleOrderPage.getModuleId());
            moduleAccessTemp.setModule(module);
            moduleAccessTemp.setExpireDate(expireDateLast);

            boolean maFlag = this.moduleAccessService.add(moduleAccessTemp);
            if (maFlag){
                logger.info("模块访问记录插入成功");
            }else{
                logger.info("模块访问记录插入失败");
            }
        }

        boolean b1 = userModuleOrderService.add(userModuleOrderPage);
        if (b1){
            logger.info("延长模块期限的记录新增成功");
            j.setSuccess(true);
            j.setMsg("延长模块期限的记录新增成功");
            j.setObj(null);
        }else{
            logger.info("延长模块期限的记录新增失败");
            j.setSuccess(false);
            j.setMsg("延长模块期限的记录新增失败");
            j.setObj(null);
        }
        super.writeJson(j);
    }

    /**
     * 新增用户延期订单
     */
    public void addWhenModuleIdIsNull(){
        if(userModuleOrderPage.getMobile() == null || userModuleOrderPage.getMobile().length() == 0) {
            logger.info("UserModuleOrderPage的用户手机号为空,创建失败！");
            return;
        }
        if(userModuleOrderPage.getOpeUserId() == null) {
            logger.info("UserModuleOrderPage的销售人员为空,创建失败！");
            return;
        }

        Json j = new Json();
        User user = this.userService.getByMobile(userModuleOrderPage.getMobile());

        Date expireDate = null;
        Date expireDateLast = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        if (user.getExpiredate() == null){
            expireDate = new Date();
            expireDateLast = expireDate;
            cal.setTime(expireDate);
        }else {
            expireDate = user.getExpiredate();
            expireDateLast = expireDate;
            cal.setTime(expireDate);
        }

        if (userModuleOrderPage.getUnit() != null) {
            if (userModuleOrderPage.getDuration() != null){
                if (userModuleOrderPage.getUnit() == 1) {
                    cal.add(Calendar.YEAR, userModuleOrderPage.getDuration());
                }else if (userModuleOrderPage.getUnit() == 2){
                    cal.add(Calendar.MONTH, userModuleOrderPage.getDuration());
                }else if (userModuleOrderPage.getUnit() == 3){
                    cal.add(Calendar.DAY_OF_MONTH, userModuleOrderPage.getDuration());
                }
                expireDateLast = cal.getTime();
            }else {
                logger.info("你填入的延长时长的值为空，请重新确认");
                j.setSuccess(false);
                j.setMsg("你填入的延长时长的值为空，请重新确认");
                j.setObj(null);
                super.writeJson(j);
                return ;
            }
        } else if (userModuleOrderPage.getTime() != null){
            if (userModuleOrderPage.getTime().getTime() <= expireDate.getTime()){
                logger.info("你输入的延长日期比截止日期还要早，请确认后再输入");
                j.setSuccess(false);
                j.setMsg("你输入的延长日期比截止日期还要早，请确认后再输入");
                j.setObj(null);
                super.writeJson(j);
                return ;
            } else{
                expireDateLast = userModuleOrderPage.getTime();
            }
        }
        userModuleOrderPage.setTime(expireDateLast);

        user.setExpiredate(expireDateLast);
        boolean maFlag = this.userService.update(user);
        if (maFlag){
            logger.info("用户有效期更新成功");
        }else{
            logger.info("用户有效期更新失败");
        }

        boolean b1 = userModuleOrderService.add(userModuleOrderPage);
        if (b1){
            logger.info("延长用户期限的记录新增成功");
            j.setSuccess(true);
            j.setMsg("延长用户期限的记录新增成功");
            j.setObj(null);
        }else{
            logger.info("延长用户期限的记录新增失败");
            j.setSuccess(false);
            j.setMsg("延长用户期限的记录新增失败");
            j.setObj(null);
        }
        super.writeJson(j);
    }

    /**
     * 检查UserModuleOrderPage的信息是否合法
     */
    public boolean checkUserModuleOrderPageMessage(UserModuleOrderPage userModuleOrderPage1){
        if(userModuleOrderPage1.getMobile() == null || userModuleOrderPage1.getMobile().length() == 0) {
            logger.info("UserModuleOrderPage的用户手机号为空,创建失败！");
            return false;
        }
        if(userModuleOrderPage1.getModuleId() == null) {
            logger.info("UserModuleOrderPage的模块名称为空,创建失败！");
            return false;
        }
        if(userModuleOrderPage1.getOpeUserId() == null) {
            logger.info("UserModuleOrderPage的销售人员为空,创建失败！");
            return false;
        }
        return true;
    }
}
