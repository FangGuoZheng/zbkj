package com.portal.dao.impl;

import com.portal.dao.UserModuleOrderDaoI;
import com.portal.model.UserModuleOrder;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FGZ on 2017/12/1.
 */
@Repository("UserModuleOrderDao")
public class UserModuleOrderDaoImpl extends BaseDaoImpl<UserModuleOrder> implements UserModuleOrderDaoI {
    @Override
    public List<UserModuleOrder> get(Integer staffId, String  userName, String moduleName, Integer opeUserId) {
        String hql = "from UserModuleOrder u where u.deleteflag=false";
        if (staffId != null)
            hql += " and u.user.sale.id="+staffId;
        if (userName != null && !"".equals(userName))
            hql += " and u.user.name like '%"+userName+"%'";
        if (moduleName != null && !"".equals(moduleName))
            hql += " and u.module.name like '%"+moduleName+"%'";
        if (opeUserId != null)
            hql += " and u.opeUser.id="+opeUserId;
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public List<UserModuleOrder> getAllByOpeUser(Integer opeUserId) {
        String hql = "from UserModuleOrder u where u.deleteflag=false";
        if (opeUserId != null)
            hql += " and u.opeUser.id="+opeUserId;
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public List<UserModuleOrder> getALLByUser(Integer userId) {
        String hql = "from UserModuleOrder u where u.deleteflag=false";
        if (userId != null)
            hql += " and u.user.id="+userId;
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public UserModuleOrder getById(Integer id) {
        String hql = "from UserModuleOrder u where u.deleteflag=false";
        if (id != null)
            hql += " and u.id="+id;
        return this.get(hql);
    }

    @Override
    public boolean add(UserModuleOrder userModuleOrder) {
        try{
            this.save(userModuleOrder);
            this.getCurrentSession().flush();
            if(userModuleOrder.getId()!=null)
                return true;
        }
        catch(Exception e)
        {
            System.out.println("新增订单产生的异常为："+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String hql="Update UserModuleOrder set deleteflag=true where id="+id;
        int count=this.executeHql(hql);
        if(count>=0)
            return true;
        else
            return false;
    }
}
