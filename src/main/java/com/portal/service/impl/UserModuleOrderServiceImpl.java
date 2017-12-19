package com.portal.service.impl;

import com.portal.dao.*;
import com.portal.model.*;
import com.portal.pageModel.UserModuleOrderPage;
import com.portal.service.UserModuleOrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FGZ on 2017/12/1.
 */
@Service("UserModuleOrderService")
public class UserModuleOrderServiceImpl implements UserModuleOrderServiceI {
    private UserModuleOrderDaoI userModuleOrderDao;
    @Autowired
    public void setUserModuleOrderDao(UserModuleOrderDaoI userModuleOrderDao) {
        this.userModuleOrderDao = userModuleOrderDao;
    }

    private UserDaoI userDao;
    @Autowired
    public void setUserDao(UserDaoI userDao) {
        this.userDao = userDao;
    }

    private ModuleDaoI moduleDao;
    @Autowired
    public void setModuleDao(ModuleDaoI moduleDao) {
        this.moduleDao = moduleDao;
    }

    private StaffDaoI staffDao;
    @Autowired
    public void setStaffDao(StaffDaoI staffDao) {
        this.staffDao = staffDao;
    }

    private ModuleAccessDaoI moduleAccessDao;
    @Autowired
    public void setModuleAccessDao(ModuleAccessDaoI moduleAccessDao) {
        this.moduleAccessDao = moduleAccessDao;
    }

    @Override
    public List<UserModuleOrder> getAllByOpeUser(Integer opeUserId) {
        return this.userModuleOrderDao.getAllByOpeUser(opeUserId);
    }

    @Override
    public List<UserModuleOrder> getALLByUser(Integer subsId) {
        return this.userModuleOrderDao.getALLByUser(subsId);
    }

    @Override
    public UserModuleOrder getById(Integer id) {
        return this.userModuleOrderDao.getById(id);
    }

    @Override
    public boolean add(UserModuleOrderPage userModuleOrderPage) {
        if (userModuleOrderPage == null)
            return false;
        UserModuleOrder userModuleOrder = new UserModuleOrder();

        if (userModuleOrderPage.getMobile() != null){
            User user = this.userDao.getByMobile(userModuleOrderPage.getMobile());
            userModuleOrder.setUser(user);
        }
        if (userModuleOrderPage.getModuleId() != null){
            Module module = this.moduleDao.get(userModuleOrderPage.getModuleId());
            userModuleOrder.setModule(module);
        }
        if (userModuleOrderPage.getOpeUserId() != null){
            Staff opeUser = this.staffDao.getById(userModuleOrderPage.getOpeUserId());
            userModuleOrder.setOpeUser(opeUser);
        }
        userModuleOrder.setAmount(userModuleOrderPage.getAmount());
        userModuleOrder.setTime(userModuleOrderPage.getTime());
        userModuleOrder.setDescription(userModuleOrderPage.getDescription());

        return this.userModuleOrderDao.add(userModuleOrder);
    }

    @Override
    public boolean delete(Integer id) {
        return this.userModuleOrderDao.delete(id);
    }
}
