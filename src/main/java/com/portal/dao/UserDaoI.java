package com.portal.dao;

import com.portal.model.User;

import java.util.List;

/**
 * Created by FGZ on 2017/10/31.
 */
public interface UserDaoI extends BaseDaoI<User>{

    public List<User> getALLBySaleId(Integer saleId, String name, String nickname, String mobile, String email, int pageSize, int page);
    public List<User> getAllByOnlySaleId(Integer saleId);
    public int getAccount(Integer saleId, String name, String nickname, String mobile, String email);
    public boolean add(User user);
    public boolean delete(Integer id);
    public boolean updateUser(User user);
    public User getById(Integer id);
    public User getByMobile(String mobile);
    public User login(String mobile, String password);
    public User verifyPassword(Integer id, String password);

}
