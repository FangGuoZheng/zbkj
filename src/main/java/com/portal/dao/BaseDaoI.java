package com.portal.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {
	/*
	 * 保持用户信息
	 */
	public Serializable save(T o);

	// 查询用户，并返回用户信息
	public T get(Class<T> c, Serializable id); // 用ID查询

	public T get(String hql);

	public T get(String hql, Object[] params);

	public T get(String hql, Map<String, Object> params);

	// 删除
	public void delete(T o);

	// 修改
	public void update(T o);

	// 保持修改
	public void saveOrUpdate(T o);

	// 查询多项值
	public List<T> find(String hql);


	// 查询结果的总数

	// 按照参数来查找数据库
	public List<T> find(String hql, Map<String, Object> params);

	// 不穿参数，有翻页功能
	public List<T> find(String hql, int page, int rows);

	// 传参数 和翻页功能
	// 按照分页的形式查找数据库 page是页数，rows是一页多少行
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	// 查询结果的总数

	public Long count(String hql);


	// 带参数的查询结果的总数
	public Long count(String hql, Map<String, Object> params);

	// 带参数的查询结果的总数
	public int countInt(String hql, Map<String, Object> params);


	// 执行一个sql语句
	public int executeHql(String hql);

	// 执行一个带参sql语句
	public int executeHql(String hql, Map<String, Object> params);

	// 多表关联查询, 返回一个List集合，需要自己解析数据
	public List<Object[]> relationalQuery(String hql);

	// 带参数的多表关联查询，返回一个List集合，需要自己解析数据
	public List<Object[]> relationalQuery(String hql, Map<String, Object> params);

	// 传参数 和翻页功能
	// 按照分页的形式查找数据库 page是页数，rows是一页多少行
	public List<Object[]> relationalQuery(String hql, Map<String, Object> params, int page, int rows);

}
