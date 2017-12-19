package com.portal.dao.impl;

import com.portal.dao.DictionaryDaoI;
import com.portal.model.Dictionary;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FGZ on 2017/12/4.
 */
@Repository("DictionaryDao")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements DictionaryDaoI{
    @Override
    public Dictionary get(Integer id) {
        String hql = "from Dictionary d where d.id="+id;
        return this.get(hql);
    }

    @Override
    public List<Dictionary> getAll() {
        String hql = "from Dictionary ";
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }
}
