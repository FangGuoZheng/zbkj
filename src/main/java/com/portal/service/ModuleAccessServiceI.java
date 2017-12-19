package com.portal.service;

import com.portal.model.ModuleAccess;

import java.util.List;

/**
 * Created by FGZ on 2017/12/7.
 */
public interface ModuleAccessServiceI {
    public List<ModuleAccess> getAll();
    public ModuleAccess get(Integer id);
    public ModuleAccess getByUserIdAndModuleId(Integer userId, Integer moduleId);
    public boolean updateExpireDateByUserIdAndModuleId(ModuleAccess moduleAccess);
    public boolean add(ModuleAccess moduleAccess);
}
