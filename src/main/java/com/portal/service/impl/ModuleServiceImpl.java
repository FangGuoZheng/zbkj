package com.portal.service.impl;

import com.portal.dao.ModuleDaoI;
import com.portal.model.Module;
import com.portal.service.ModuleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FGZ on 2017/11/29.
 */
@Service("ModuleService")
public class ModuleServiceImpl implements ModuleServiceI{
    private ModuleDaoI moduleDao;
    @Autowired
    public void setModuleDao(ModuleDaoI moduleDao) {
        this.moduleDao = moduleDao;
    }

    @Override
    public List<Module> getAll() {
        return this.moduleDao.getAll();
    }

    @Override
    public List<Module> getModulesByTypeId(Integer typeId) {
        return this.moduleDao.getModulesByTypeId(typeId);
    }

    @Override
    public Module get(Integer id) {
        return this.moduleDao.get(id);
    }
}
