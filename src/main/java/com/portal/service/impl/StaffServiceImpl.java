package com.portal.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.portal.dao.RoleDaoI;
import com.portal.model.Role;
import com.portal.pageModel.SessionInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.StaffDaoI;
import com.portal.model.Staff;
import com.portal.pageModel.StaffPage;
import com.portal.service.StaffServiceI;

@Service("StaffService")
public class StaffServiceImpl implements StaffServiceI {

	// Logger for this class
	private static final Logger logger = Logger.getLogger(StaffServiceImpl.class);

	private StaffDaoI staffDao;
	@Autowired
	public void setStaffDao(StaffDaoI staffDao) {
		this.staffDao = staffDao;
	}

	private RoleDaoI roleDao;
	@Autowired
	public void setRoleDao(RoleDaoI roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public boolean verifyPassword(StaffPage staffPage) {
		Staff staff = staffDao.verifyPassword(staffPage.getStaffId(), staffPage.getPassword());
		if (staff == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public SessionInfo login(StaffPage staffPage) {
		Staff staff = staffDao.login(staffPage.getAccount(), staffPage.getPassword());
		if (staff == null) {
			// logger.info("++++++++数据库中查找不到该用户++++++++");
			return null;
		} else {
			// logger.info("用户名:" + staff.getStaffname() + "密码:" + staff.getPassword());
			SessionInfo sessionInfo = new SessionInfo();

			sessionInfo.setStaffId(staff.getId());
			sessionInfo.setLoginName(staff.getAccount());
			sessionInfo.setLoginPassword(staff.getPassword());
			sessionInfo.setName(staff.getName());
			sessionInfo.setPhone(staff.getPhone());
			sessionInfo.setEmail(staff.getEmail());
			sessionInfo.setRoleId(staff.getRole().getId());
			sessionInfo.setRoleName(staff.getRole().getName());
			sessionInfo.setCreateTime(staff.getCreateTime().getTime());

			return sessionInfo;
		}
	}

	@Override
	public List<StaffPage> getAll(String roleName, String account, String name,
									  String phone, String email, int pageSize, int page) {
		// TODO Auto-generated method stub
		List<Staff> list=staffDao.getAll(roleName,account,name,phone,email,pageSize,page);
		List<StaffPage> pageList=new ArrayList<StaffPage>();
		for(int i=0;i<list.size();i++)
		{
			Staff staff=list.get(i);
			StaffPage p=new StaffPage();
			p.setStaffId(staff.getId());
			p.setAccount(staff.getAccount());
			p.setName(staff.getName());
			p.setEmail(staff.getEmail());
			p.setPhone(staff.getPhone());
			p.setRoleId(staff.getRole().getId());
			p.setRoleName(staff.getRole().getName());
			pageList.add(p);
		}
		return pageList;
	}
	@Override
	public int getAllCount(String roleName, String account, String name,
			String phone, String email) {
		// TODO Auto-generated method stub
		int count=staffDao.getAllCount(roleName, account, name, phone, email);
		return count;
	}
	@Override
	public boolean add(StaffPage staff) {
		// TODO Auto-generated method stub
		Staff dstaff=new Staff();

		dstaff.setAccount(staff.getAccount());
		dstaff.setPassword(staff.getPassword());
		dstaff.setName(staff.getName());
		dstaff.setPhone(staff.getPhone());
		dstaff.setEmail(staff.getEmail());

		Role role = roleDao.getRoleById(staff.getRoleId());
		dstaff.setRole(role);
		dstaff.setCreateTime(new Date());
		dstaff.setDeleteflag(false);
		dstaff.setQuestion(staff.getQuestion());
		dstaff.setAnswer(staff.getAnswer());

		return staffDao.add(dstaff);
	}
	@Override
	public StaffPage getByAccount(String account) {
		// TODO Auto-generated method stub
		Staff staff=staffDao.getByAccount(account);
		if(staff!=null)
		{
			StaffPage page=new StaffPage();
			page.setStaffId(staff.getId());
			page.setAccount(staff.getAccount());
			page.setRoleId(staff.getRole().getId());
			page.setRoleName(staff.getRole().getName());
			page.setName(staff.getName());
			page.setPhone(staff.getPhone());
			page.setEmail(staff.getEmail());
			page.setQuestion(staff.getQuestion());
			page.setAnswer(staff.getAnswer());
			return page;
		}
		else
			return null;
	}

	@Override
	public StaffPage getById(Integer staffId) {
		Staff staff = staffDao.getById(staffId);
		if(staff!=null) {
			StaffPage page=new StaffPage();
			page.setStaffId(staff.getId());
			page.setAccount(staff.getAccount());
			page.setRoleId(staff.getRole().getId());
			page.setRoleName(staff.getRole().getName());
			page.setName(staff.getName());
			page.setEmail(staff.getEmail());
			page.setPhone(staff.getPhone());
			page.setQuestion(staff.getQuestion());
			page.setAnswer(staff.getAnswer());
			return page;
		} else{
			return null;
		}
	}

	@Override
	public boolean deleteByAccount(String account) {
		// TODO Auto-generated method stub
		return staffDao.deleteByAccount(account);
	}

	@Override
	public boolean deleteById(Integer id) {
		return staffDao.deleteById(id);
	}

	@Override
	public boolean edit(StaffPage staffPage) {
		// TODO Auto-generated method stub
		Staff staff=staffDao.getById(staffPage.getStaffId());
		if(staff==null)
			return false;
		staff.setAccount(staffPage.getAccount());
		staff.setName(staffPage.getName());
		staff.setEmail(staffPage.getEmail());
		staff.setPhone(staffPage.getPhone());
		if (staffPage.getRoleId() != null){
			Role role = roleDao.getRoleById(staffPage.getRoleId());
			staff.setRole(role);
		}
		staff.setQuestion(staffPage.getQuestion());
		staff.setAnswer(staffPage.getAnswer());
		staffDao.edit(staff);
		return true;
	}
	@Override
	public boolean editPassword(StaffPage staffPage) {
		// TODO Auto-generated method stub
		Staff staff=staffDao.getById(staffPage.getStaffId());
		if(staff==null)
			return false;
		staff.setPassword(staffPage.getPassword());
		staffDao.edit(staff);
		return true;
	}

	@Override
	public List<Staff> getByRoleId(Integer roleId) {
		return staffDao.getByRoleId(roleId);
	}
}
