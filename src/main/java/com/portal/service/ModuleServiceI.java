package com.portal.service;

import com.portal.model.Module;

import java.util.List;

/**
 * Created by FGZ on 2017/11/29.
 */
public interface ModuleServiceI {
    public Module get(Integer id);
    public List<Module> getAll();
    public List<Module> getModulesByTypeId(Integer typeId);
}
