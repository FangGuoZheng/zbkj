package com.portal.service;

import java.util.List;

import com.portal.model.Staff;
import com.portal.pageModel.SessionInfo;
import com.portal.pageModel.StaffPage;

public interface StaffServiceI {
	public List<StaffPage> getAll(String roleName, String account, String name, String phone, String email, int pageSize, int page);  //获取所有用户信息
	public int getAllCount(String roleName,String account,String name,String phone,String email);  //获取用户的数目
	public boolean add(StaffPage staffPage);  //添加用户
	public StaffPage getByAccount(String account);  //根据账号获取用户信息
	public StaffPage getById(Integer staffId);  //根据id获取用户信息
	public boolean deleteByAccount(String account);
	public boolean deleteById(Integer id);
	public boolean edit(StaffPage staffPage);  //修改用户信息
	public boolean editPassword(StaffPage staffPage);  //修改密码
	public SessionInfo login(StaffPage staffPage);
	public boolean verifyPassword(StaffPage staffPage);
	public List<Staff> getByRoleId(Integer roleId);
}
