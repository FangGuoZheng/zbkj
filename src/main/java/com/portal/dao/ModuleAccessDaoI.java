package com.portal.dao;

import com.portal.model.ModuleAccess;

import java.util.List;

/**
 * Created by FGZ on 2017/12/4.
 */
public interface ModuleAccessDaoI extends BaseDaoI<ModuleAccess>{
    public List<ModuleAccess> getAll();
    public ModuleAccess get(Integer id);
    public ModuleAccess getByUserIdAndModuleId(Integer userId, Integer moduleId);
    public boolean updateExpireDateByUserIdAndModuleId(ModuleAccess moduleAccess);
    public boolean add(ModuleAccess moduleAccess);
}
