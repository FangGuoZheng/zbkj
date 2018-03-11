package com.portal.dao.impl;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.portal.dao.StaffDaoI;
import com.portal.model.Staff;

@Repository("StaffDao")
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDaoI {
	@Override
	public Staff verifyPassword(Integer id, String password) {
		String hql = "from Staff s where s.id="+id+" and s.password='"+password+"' and s.deleteflag=false";
		Staff staff=this.get(hql);
		return staff;
	}

	@Override
	public Staff login(String account, String password) {
		String hql = "from Staff s where s.account='"+account+"' and s.password='"+password+"' and s.deleteflag=false";
		Staff staff=this.get(hql);
		return staff;
	}

	@Override
	public List<Staff> getAll(Integer roleId, String rolename, String account, String name, String phone, String email, int pageSize, int page) {
		// TODO Auto-generated method stub
		String hql="from Staff s where s.deleteflag=false";
		if(roleId != null) {
			if(roleId == 13){
				hql += " and (s.role.id=1 or s.role.id=3)";
			}else {
				hql += " and s.role.id=" + roleId + "";
			}
		}
		if(rolename != null && !"".equals(rolename))
			hql+=" and s.role.roleName like '%"+rolename+"%'";
		if(account != null && !"".equals(account))
			hql+=" and s.account like '%"+account+"%'";
		if(name != null && !"".equals(name))
			hql+=" and s.name like '%"+name+"%'";
		if(phone != null && !"".equals(phone))
			hql+=" and s.phone like '%"+phone+"%'";
		if(email != null && !"".equals(email))
			hql+=" and s.email like '%"+email+"%'";
		hql+=" order by s.id asc";
		Query q = this.getCurrentSession().createQuery(hql);
		int startIndex=(page-1)*pageSize;
		q.setFirstResult(startIndex);
		q.setMaxResults(pageSize);
		return q.list();
	}

	@Override
	public int getAllCount(String roleName, String account, String name,
			String phone, String email) {
		// TODO Auto-generated method stub
		String hql="select count(s) from Staff s where s.deleteflag=false";
		if(roleName != null)
			hql+=" and s.role.roleName='"+roleName+"'";
		if(account != null)
			hql+=" and s.account='"+account+"'";
		if(name != null)
			hql+=" and s.name='"+name+"'";
		if(phone != null)
			hql+=" and s.phone='"+phone+"'";
		if(email != null)
			hql+=" and s.email='"+email+"'";
		long count=this.count(hql).longValue();		
		return (int)count;
	}

	@Override
	public boolean add(Staff staff) {
		// TODO Auto-generated method stub
		try{
			this.save(staff);
            this.getCurrentSession().flush();
            if(staff.getId()!=null)
            	return true;
		}
		catch(Exception e)
		{
			System.out.println("新增员工产生的异常为："+e.getMessage());
		}
		return false;
	}

	@Override
	public Staff getByAccount(String account) {
		// TODO Auto-generated method stub
		String hql="from Staff where deleteflag=false and account='"+account+"'";
		Staff staff=this.get(hql);
		return staff;
	}

	@Override
	public Staff getById(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Staff s where s.id="+id+" and s.deleteflag=false";
		Staff staff=this.get(hql);
		return staff;
	}

	@Override
	public boolean deleteByAccount(String account) {
		// TODO Auto-generated method stub
		String hql="Update Staff set deleteflag=true where account='"+account+"'";
		int count=this.executeHql(hql);
		if(count>=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		String hql="Update Staff set deleteflag=true where id="+id;
		int count=this.executeHql(hql);
		if(count>=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean edit(Staff staff) {
		// TODO Auto-generated method stub
		this.saveOrUpdate(staff);
		this.getCurrentSession().flush();
		return true;
	}

	@Override
	public List<Staff> getByRoleId(Integer roleId) {
		String hql="from Staff s where s.deleteflag=false and s.role.id="+roleId;
		hql+=" order by s.id asc";
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}
}
