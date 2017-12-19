package com.portal.dao;

import com.portal.model.UserModuleOrder;

import java.util.List;

/**
 * Created by FGZ on 2017/12/1.
 */
public interface UserModuleOrderDaoI extends BaseDaoI<UserModuleOrder> {
    public List<UserModuleOrder> getAllByOpeUser(Integer opeUserId);
    public List<UserModuleOrder> getALLByUser(Integer userId);
    public UserModuleOrder getById(Integer id);
    public boolean add(UserModuleOrder userModuleOrder);
    public boolean delete(Integer id);
}
