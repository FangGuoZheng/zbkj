package com.portal.action;

import com.opensymphony.xwork2.ModelDriven;
import com.portal.model.User;
import com.portal.pageModel.Json;
import com.portal.pageModel.UserPage;
import com.portal.service.UserServiceI;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by FGZ on 2017/11/3.
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "UserAction")
public class UserAction extends BaseAction implements ModelDriven<UserPage> {

    private static final long serialVersionUID = 4426765055782995839L;
    private static final Logger logger = Logger.getLogger(UserAction.class);

    private UserServiceI userService;
    @Autowired
    public void setUserService(UserServiceI userService) {
        this.userService = userService;
    }

    UserPage userPage = new UserPage();
    @Override
    public UserPage getModel() {
        return userPage;
    }

    /**
     * 根据条件获取用户列表，分页
     */
    public void getALLBySaleId(){
        if(userPage.getRoleId() == 1){
            userPage.setSaleId(null);
        }
        List<UserPage> list = this.userService.getALLBySaleId(userPage.getSaleId(), userPage.getName(), userPage.getNickname(), userPage.getMobile(), userPage.getEmail(), userPage.getPageSize(), userPage.getPage());
        Json j = new Json();
        if (list != null && list.size() != 0){
            logger.info("查询用户成功");
            j.setSuccess(true);
            j.setMsg("查询用户成功");
            j.setObj(list);
        } else{
            logger.info("查询用户失败");
            j.setSuccess(false);
            j.setMsg("查询用户失败");
            j.setObj(null);
        }
        super.writeJson(j);
    }

    /**
     * 根据saleId获取用户列表
     */
    public void getAllByOnlySaleId(){
        List<User> list = this.userService.getAllByOnlySaleId(userPage.getSaleId());
        Json j = new Json();
        if (list != null && list.size() != 0){
            logger.info("查询用户成功");
            j.setSuccess(true);
            j.setMsg("查询用户成功");
            j.setObj(list);
        } else{
            logger.info("查询用户失败");
            j.setSuccess(false);
            j.setMsg("查询用户失败");
            j.setObj(null);
        }
        super.writeJson(j);
    }

    /**
     * 根据查询条件获取用户的数目
     */
    public void getAccount(){
        int count = this.userService.getAccount(userPage.getSaleId(), userPage.getName(), userPage.getNickname(), userPage.getMobile(), userPage.getEmail());
        Json j = new Json();
        logger.info("查询用户数目成功");
        j.setSuccess(true);
        j.setMsg("查询用户数目成功");
        j.setObj(count);
        super.writeJson(j);
    }

    /**
     * 根据mobile来获取用户
     */
    public void getByMobile(){
        User temp = userService.getByMobile(userPage.getMobile());
        Json json = new Json();
        if (temp != null){
            json.setSuccess(true);
            json.setMsg("查询用户成功");
            json.setObj(temp);
        } else{
            json.setSuccess(false);
            json.setMsg("查询用户失败");
            json.setObj(temp);
        }
        super.writeJson(json);
    }

    /**
     * 新增用户
     */
    public void add(){
        User temp = userService.getByMobile(userPage.getMobile());
        Json json = new Json();
        if (temp != null){
            logger.info("用户已存在，请直接查看");
            json.setSuccess(false);
            json.setMsg("用户已存在，请直接查看");
            json.setObj(null);
            super.writeJson(json);
            return;
        }

        UserPage userPage1 = getUserByURL(userPage.getAreacode(), userPage.getMobile(), userPage.getSaleId());
        if (userPage1 == null){
            logger.info("不存在此用户，请检查用户手机号是否正确");
            json.setSuccess(false);
            json.setMsg("不存在此用户，请检查用户手机号是否正确");
            json.setObj(false);
            super.writeJson(json);
            return;
        }

        if (userPage1.getId()!=null && userPage1.getId() == 0){
            logger.info("接口调用出错");
            json.setSuccess(false);
            json.setMsg("接口调用出错");
            json.setObj(false);
            super.writeJson(json);
            return;
        }

        if (userPage1.getPassword() != null){
            userPage1.setPassword(toSHA256Code(userPage1.getPassword()));
        } else{
            userPage1.setPassword(toSHA256Code(userPage1.getMobile()));  // 新建用户的时候需要对用户初始化一个密码，密码为手机号。
        }
        boolean flag = this.userService.add(userPage1);

        if (flag == true){
            logger.info("添加用户成功");
            json.setSuccess(true);
            json.setMsg("添加用户成功");
            json.setObj(true);
        } else{
            logger.info("添加用户失败");
            json.setSuccess(false);
            json.setMsg("添加用户失败");
            json.setObj(false);
        }
        super.writeJson(json);
    }

    /**
     * 删除用户
     */
    public void delete(){
        Integer subId = userPage.getId();
        Json j = new Json();
        if (subId == null){
            logger.info("subId is null, please checkout.");
            j.setSuccess(false);
            j.setMsg("删除用户失败");
            j.setObj(false);
            super.writeJson(j);
        } else {
            boolean result = this.userService.delete(subId);
            if(result) {
                j.setSuccess(true);
                j.setMsg("删除用户成功");
                j.setObj(true);
            } else {
                j.setSuccess(false);
                j.setMsg("删除用户失败");
                j.setObj(false);
            }
            super.writeJson(j);
        }
    }

    /**
     * 修改用户
     */
    public void update(){
        if (userPage.getPassword() != null){
            userPage.setPassword(toSHA256Code(userPage.getPassword()));
        }
        boolean result = this.userService.update(userPage);
        Json j = new Json();
        if (result){
            logger.info("修改用户成功 mobile:" + userPage.getMobile());
            j.setSuccess(true);
            j.setMsg("修改用户成功");
            j.setObj(true);
            super.writeJson(j);
        } else{
            logger.info("修改用户失败");
            j.setSuccess(false);
            j.setMsg("修改用户失败");
            j.setObj(false);
            super.writeJson(j);
        }
    }

    /**
     * 通过id来获取用户
     */
    public void getUserById(){
        User user = this.userService.getById(userPage.getId());
        Json j = new Json();
        if (user != null){
            j.setSuccess(true);
            j.setMsg("查询用户成功");
            j.setObj(user);
        } else{
            j.setSuccess(false);
            j.setMsg("查询用户失败");
            j.setObj(null);
        }
        super.writeJson(j);
    }
}
