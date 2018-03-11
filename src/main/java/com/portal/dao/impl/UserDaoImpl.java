package com.portal.dao.impl;

import com.portal.dao.UserDaoI;
import com.portal.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FGZ on 2017/11/1.
 */
@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDaoI {

    @Override
    public List<User> getALLBySaleId(Integer saleId, String name, String nickname, String mobile, String email, int pageSize, int page) {
        String hql = "from User u where u.deleteflag=false ";
        if (saleId != null)
            hql += "and u.sale.id="+saleId+" ";
        if (name != null && !"".equals(name))
            hql += "and u.name like '%"+name+"%' ";
        if (nickname != null && !"".equals(nickname))
            hql += "and u.nickname like '%"+nickname+"%' ";
        if (mobile != null && !"".equals(mobile))
            hql += "and u.mobile like '%"+mobile+"%' ";
        if (email != null && !"".equals(email))
            hql += "and u.email like '%"+email+"%' ";
        Query q = this.getCurrentSession().createQuery(hql);
        int startIndex=(page-1)*pageSize;
        q.setFirstResult(startIndex);
        q.setMaxResults(pageSize);
        return q.list();
    }

    @Override
    public List<User> getAllByOnlySaleId(Integer saleId) {
        String hql = "from User u where u.deleteflag=false ";
        if (saleId != null)
            hql += "and u.sale.id="+saleId+" ";
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public int getAccount(Integer saleId, String name, String nickname, String mobile, String email) {
        String hql = "select count(u) from User u where u.deleteflag=false ";
        if (saleId != null)
            hql += "and u.sale.id="+saleId+" ";
        if (name != null)
            hql += "and u.name='"+name+"' ";
        if (nickname != null)
            hql += "and u.nickname='"+nickname+"' ";
        if (mobile != null)
            hql += "and u.mobile='"+mobile+"' ";
        if (email != null)
            hql += "and u.email='"+email+"' ";
        int count = (int)this.count(hql).longValue();
        return count;
    }

    @Override
    public boolean add(User user) {
        try{
            this.save(user);
            this.getCurrentSession().flush();
            if(user.getId()!=null)
                return true;
        }
        catch(Exception e) {
            System.out.println("新增用户产生的异常为："+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
//        String hql="Update User set deleteflag=true where id="+id;
        String hql="delete from User where id="+id;
        int count=this.executeHql(hql);
        if(count>=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateUser(User user) {
        this.saveOrUpdate(user);
        this.getCurrentSession().flush();
        return true;
    }

    @Override
    public User getById(Integer id) {
        String hql = "from User u where u.id="+id+" and u.deleteflag=false";
        User user = this.get(hql);
        return user;
    }

    @Override
    public User getByMobile(String mobile) {
        String hql = "from User u where u.mobile='"+mobile+"' and u.deleteflag=false";
        User user = this.get(hql);
        return user;
    }

    @Override
    public User login(String mobile, String password) {
        String hql = "from User u where u.mobile='"+mobile+"' and u.password='"+password+"' and u.deleteflag=false and u.role.id=6";
        User user = this.get(hql);
        return user;
    }

    @Override
    public User verifyPassword(Integer id, String password) {
        String hql = "from User u where u.id="+id+" and u.password='"+password+"' and u.deleteflag=false";
        User user = this.get(hql);
        return user;
    }
}
