package com.portal.dao.impl;

import com.portal.dao.ModuleAccessDaoI;
import com.portal.model.ModuleAccess;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FGZ on 2017/12/6.
 */
@Repository("ModuleAccessDao")
public class ModuleAccessDaoImpl extends BaseDaoImpl<ModuleAccess> implements ModuleAccessDaoI {
    @Override
    public List<ModuleAccess> getAll() {
        String hql = "from ModuleAccess m where m.deleteflag=false ";
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public ModuleAccess get(Integer id) {
        String hql = "from ModuleAccess m where m.deleteflag=false and m.id="+id;
        return this.get(hql);
    }

    @Override
    public ModuleAccess getByUserIdAndModuleId(Integer userId, Integer moduleId) {
        String hql = "from ModuleAccess m where m.deleteflag=false and m.user.id="+userId+" and m.module.id="+moduleId;
        return this.get(hql);
    }

    @Override
    public boolean updateExpireDateByUserIdAndModuleId(ModuleAccess moduleAccess) {
        this.saveOrUpdate(moduleAccess);
        this.getCurrentSession().flush();
        return true;
    }

    @Override
    public boolean add(ModuleAccess moduleAccess) {
        try{
            this.save(moduleAccess);
            this.getCurrentSession().flush();
            if(moduleAccess.getId()!=null) {
                return true;
            }
        } catch(Exception e)
        {
            System.out.println("新增ModuleAccess产生的异常为："+e.getMessage());
        }
        return false;
    }
}
