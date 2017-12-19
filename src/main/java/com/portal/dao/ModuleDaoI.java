package com.portal.dao;

import com.portal.model.Module;

import java.util.List;

/**
 * Created by FGZ on 2017/11/29.
 */
public interface ModuleDaoI extends BaseDaoI<Module> {
    public Module get(Integer id);
    public List<Module> getAll();
    public List<Module> getModulesByTypeId(Integer typeId);
}
