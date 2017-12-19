package com.portal.dao.impl;

import com.portal.dao.RoleDaoI;
import com.portal.model.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FGZ on 2017/10/10.
 */
@Repository("RoleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDaoI{
    @Override
    public List<Role> getAllRole() {
        // TODO Auto-generated method stub
        String hql = "from Role r";
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public Role getRoleByName(String name) {
        // TODO Auto-generated method stub
        String hql = "from Role r where r.name = '"+name+"'";
        Role role = this.get(hql);
        return role;
    }

    @Override
    public Role getRoleById(Integer roleId) {
        String hql = "from Role r where r.id="+roleId;
        Role role = this.get(hql);
        return role;
    }

    @Override
    public boolean addRole(Role role) {
        // TODO Auto-generated method stub
        try{
            this.save(role);
            this.getCurrentSession().flush();
            if(role.getId()!=null)
                return true;
        }
        catch(Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteRole(String name) {
        // TODO Auto-generated method stub
        String hql="delete Role r where r.name='"+name+"'";
        int count=this.executeHql(hql);
        if(count>=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateRole(Role role) {
        // TODO Auto-generated method stub
        this.saveOrUpdate(role);
        this.getCurrentSession().flush();
        return true;
    }
}
