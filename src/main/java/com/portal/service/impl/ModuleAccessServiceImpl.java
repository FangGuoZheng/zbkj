package com.portal.service.impl;

import com.portal.dao.ModuleAccessDaoI;
import com.portal.model.ModuleAccess;
import com.portal.service.ModuleAccessServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FGZ on 2017/12/7.
 */
@Service("ModuleAccessService")
public class ModuleAccessServiceImpl implements ModuleAccessServiceI {
    private ModuleAccessDaoI moduleAccessDao;
    @Autowired
    public void setModuleAccessDao(ModuleAccessDaoI moduleAccessDao) {
        this.moduleAccessDao = moduleAccessDao;
    }

    @Override
    public List<ModuleAccess> getAll() {
        return this.moduleAccessDao.getAll();
    }

    @Override
    public ModuleAccess get(Integer id) {
        return this.moduleAccessDao.get(id);
    }

    @Override
    public ModuleAccess getByUserIdAndModuleId(Integer userId, Integer moduleId) {
        return this.moduleAccessDao.getByUserIdAndModuleId(userId, moduleId);
    }

    @Override
    public boolean updateExpireDateByUserIdAndModuleId(ModuleAccess moduleAccess) {
        return this.moduleAccessDao.updateExpireDateByUserIdAndModuleId(moduleAccess);
    }

    @Override
    public boolean add(ModuleAccess moduleAccess) {
        return this.moduleAccessDao.add(moduleAccess);
    }
}
