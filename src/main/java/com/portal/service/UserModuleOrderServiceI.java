package com.portal.service;

import com.portal.model.UserModuleOrder;
import com.portal.pageModel.UserModuleOrderPage;

import java.util.List;

/**
 * Created by FGZ on 2017/12/1.
 */
public interface UserModuleOrderServiceI {
    public List<UserModuleOrderPage> get(Integer staffId, String userName, String moduleName, Integer opeUserId);
    public List<UserModuleOrder> getAllByOpeUser(Integer opeUserId);
    public List<UserModuleOrder> getALLByUser(Integer userId);
    public UserModuleOrder getById(Integer id);
    public boolean add(UserModuleOrderPage userModuleOrderPage);
    public boolean delete(Integer id);
}
