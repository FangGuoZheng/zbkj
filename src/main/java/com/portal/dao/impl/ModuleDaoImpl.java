package com.portal.dao.impl;

import com.portal.dao.ModuleDaoI;
import com.portal.model.Module;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FGZ on 2017/11/29.
 */
@Repository("ModuleDao")
public class ModuleDaoImpl extends BaseDaoImpl<Module> implements ModuleDaoI {
    @Override
    public List<Module> getAll() {
        String hql = "from Module";
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public List<Module> getModulesByTypeId(Integer typeId) {
        String hql="from Module m where m.typeId="+typeId;
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public Module get(Integer id) {
        String hql="from Module m where m.id="+id;
        return this.get(hql);
    }
}
