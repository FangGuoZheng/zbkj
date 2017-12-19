package com.portal.service;

import com.portal.model.User;
import com.portal.pageModel.SessionInfo;
import com.portal.pageModel.UserPage;

import java.util.List;

/**
 * Created by FGZ on 2017/11/2.
 */
public interface UserServiceI {

    public List<UserPage> getALLBySaleId(Integer saleId, String name, String nickname, String mobile, String email, int pageSize, int page);
    public List<User> getAllByOnlySaleId(Integer saleId);
    public int getAccount(Integer saleId, String name, String nickname, String mobile, String email);
    public boolean add(UserPage userPage);
    public boolean delete(Integer id);
    public boolean update(UserPage userPage);
    public boolean update(User user);
    public User getById(Integer id);
    public User getByMobile(String mobile);
    public SessionInfo login(UserPage userPage);
    public boolean verifyPassword(UserPage userPage);

}
