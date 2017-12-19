package com.portal.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.dao.BaseDaoI;

@Repository("BaseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/*
	 * 注册时保存对象
	 */
	@Override
	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	/*
	 * 检查登录信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, Object[] params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/*
	 * 删除
	 */
	@Override
	public void delete(T o) {
		this.sessionFactory.getCurrentSession().delete(o);
	}

	/*
	 * 更新
	 */
	@Override
	public void update(T o) {
		this.sessionFactory.getCurrentSession().update(o);
	}

	/*
	 * 更新和保存
	 */
	@Override
	public void saveOrUpdate(T o) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(o);
	}

	/*
	 * 查询多项
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	/*
	 * 查询多项 带参数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	/*
	 * 分页显示查询结果并且带参数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = this.getCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		// （当前页-1）×每页显示记录数 为 setFirstResult的参数
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();

	}

	/*
	 * 查询多项，可分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/*
	 * 查询分页结果的总数
	 */
	@Override
	public Long count(String hql) {
		Query sum = this.getCurrentSession().createQuery(hql);
		return (Long) sum.uniqueResult();
	}

	/*
	 * 查询分页结果的总数
	 */
	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	/*
	 * 根据ID查询数据库 返回T类型的数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	/*
	 * 执行一个HQL语句
	 */
	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public int countInt(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return Integer.valueOf(((Long) q.uniqueResult()).toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> relationalQuery(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> relationalQuery(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> relationalQuery(String hql,
			Map<String, Object> params, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		// （当前页-1）×每页显示记录数 为 setFirstResult的参数
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

}
