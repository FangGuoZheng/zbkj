package com.portal.dao;


import java.util.List;

import com.portal.model.Staff;

public interface StaffDaoI extends BaseDaoI<Staff> {
	
	public List<Staff> getAll(Integer roleId, String rolename, String account, String name, String phone, String email, int pageSize, int page);
	public int getAllCount(String rolename,String account,String name,String phone,String email); //获取用户的数目
	public boolean add(Staff staff);  //添加用户
	public Staff getByAccount(String account);  //根据账号获取用户信息
	public Staff getById(Integer id);  //根据staffId获取用户
	public boolean deleteByAccount(String account);  //删除用户信息
	public boolean deleteById(Integer id);
	public boolean edit(Staff staff);  //修改用户信息
	public Staff login(String account, String password);
	public Staff verifyPassword(Integer id, String password);
	public List<Staff> getByRoleId(Integer roleId);

}
