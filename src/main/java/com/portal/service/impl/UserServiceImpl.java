package com.portal.service.impl;

import com.portal.dao.RoleDaoI;
import com.portal.dao.UserDaoI;
import com.portal.dao.StaffDaoI;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.Staff;
import com.portal.pageModel.SessionInfo;
import com.portal.pageModel.UserPage;
import com.portal.service.UserServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FGZ on 2017/11/2.
 */
@Service("UserService")
public class UserServiceImpl implements UserServiceI {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDaoI userDao;
    private RoleDaoI roleDao;
    private StaffDaoI staffDao;

    @Autowired
    public void setUserDao(UserDaoI userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDaoI roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setStaffDao(StaffDaoI staffDao) {
        this.staffDao = staffDao;
    }

    @Override
    public List<UserPage> getALLBySaleId(Integer saleId, String name, String nickname, String mobile, String email, int pageSize, int page) {
        List<User> list = this.userDao.getALLBySaleId(saleId, name, nickname, mobile, email, pageSize, page);
        List<UserPage> pageList = new ArrayList<UserPage>();
        for (User user:list){
            UserPage userPage = new UserPage();

            userPage.setId(user.getId());
            userPage.setName(user.getName());
            userPage.setNickname(user.getNickname());
            userPage.setMobile(user.getMobile());
            userPage.setEmail(user.getEmail());
            userPage.setGender(user.getGender());
            userPage.setBirthdate(user.getBirthdate());
            userPage.setLocation(user.getLocation());
            userPage.setType(user.getType());
            userPage.setAreacode(user.getAreacode());
            userPage.setAvatar(user.getAvatar());
            userPage.setSaleId(user.getSale().getId());

            pageList.add(userPage);
        }
        return pageList;
    }

    @Override
    public List<User> getAllByOnlySaleId(Integer saleId) {
        List<User> list = this.userDao.getAllByOnlySaleId(saleId);
        return list;
    }

    @Override
    public int getAccount(Integer saleId, String name, String nickname, String mobile, String email) {
        int count = this.userDao.getAccount(saleId, name, nickname, mobile, email);
        return count;
    }

    @Override
    public boolean add(UserPage userPage) {
        User user = new User();

        user.setUuid(userPage.getUuid());
        user.setName(userPage.getName());
        user.setNickname(userPage.getNickname());
        if (userPage.getAreacode() != null)
            user.setAreacode(userPage.getAreacode());
        else
            user.setAreacode("86");
        user.setMobile(userPage.getMobile());
        user.setPassword(userPage.getPassword());
        user.setAvatar(userPage.getAvatar());
        user.setEmail(userPage.getEmail());
        user.setGender(userPage.getGender());
        user.setBirthdate(userPage.getBirthdate());
        user.setLocation(userPage.getLocation());
        user.setType(userPage.getType());
        user.setExpiredate(new Date());

        Staff saleman = this.staffDao.getById(userPage.getSaleId());
        user.setSale(saleman);

        Role role =roleDao.getRoleById(6);
        user.setRole(role);

        user.setDeleteflag(false);

        return this.userDao.add(user);
    }

    @Override
    public boolean delete(Integer id) {
        return this.userDao.delete(id);
    }

    @Override
    public boolean update(UserPage userPage) {
        User user = this.userDao.getById(userPage.getId());

        user.setName(userPage.getName()!=null?userPage.getName():user.getName());
        user.setNickname(userPage.getNickname()!=null?userPage.getNickname():user.getNickname());
        user.setAreacode(userPage.getAreacode()!=null?userPage.getAreacode():user.getAreacode());
        user.setMobile(userPage.getMobile()!=null?userPage.getMobile():user.getMobile());
        if (userPage.getPassword()!=null){
            user.setPassword(userPage.getPassword());
        }
        user.setAvatar(userPage.getAvatar()!=null?userPage.getAvatar():user.getAvatar());
        user.setEmail(userPage.getEmail()!=null?userPage.getEmail():user.getEmail());
        user.setGender(userPage.getGender()!=null?userPage.getGender():user.getGender());
        user.setBirthdate(userPage.getBirthdate()!=null?userPage.getBirthdate():user.getBirthdate());
        user.setLocation(userPage.getLocation()!=null?userPage.getLocation():user.getLocation());
        if (userPage.getType()!=null){
            user.setType(userPage.getType());
        }
        if (userPage.getSaleId()!=null){
            Staff sale = this.staffDao.getById(userPage.getSaleId());
            user.setSale(sale);
        }

        return this.userDao.updateUser(user);
    }

    @Override
    public boolean update(User user) {
        return this.userDao.updateUser(user);
    }

    @Override
    public User getById(Integer id) {
        return this.userDao.getById(id);
    }

    @Override
    public User getByMobile(String mobile) {
        return this.userDao.getByMobile(mobile);
    }

    @Override
    public SessionInfo login(UserPage userPage) {
        if (userPage.getMobile()!=null && userPage.getPassword()!=null){
            User user = this.userDao.login(userPage.getMobile(), userPage.getPassword());
            if (user==null)
                return null;
            else{
                SessionInfo sessionInfo = new SessionInfo();

                sessionInfo.setUserId(user.getId());
                sessionInfo.setName(user.getName());
                sessionInfo.setNickname(user.getNickname());
                sessionInfo.setAreacode(user.getAreacode());
                sessionInfo.setMobile(user.getMobile());
                sessionInfo.setAvatar(user.getAvatar());
                sessionInfo.setGender(user.getGender());
                sessionInfo.setType(user.getType());
                sessionInfo.setExpireDate(user.getExpiredate().getTime());
                sessionInfo.setSaleId(user.getSale().getId());

                return sessionInfo;
            }
        }
        return null;
    }

    @Override
    public boolean verifyPassword(UserPage userPage) {
        User user = this.userDao.verifyPassword(userPage.getId(), userPage.getPassword());
        if (user != null){
            return true;
        }
        return false;
    }
}
