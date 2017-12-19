package com.portal.dao;

import com.portal.model.Role;

import java.util.List;

/**
 * Created by FGZ on 2017/10/10.
 */
public interface RoleDaoI {
    public List<Role> getAllRole();

    public Role getRoleByName(String name);

    public Role getRoleById(Integer roleId);

    public boolean addRole(Role role);

    public boolean deleteRole(String name);

    public boolean updateRole(Role role);
}
