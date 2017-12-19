package com.portal.action;

import com.opensymphony.xwork2.ModelDriven;
import com.portal.model.Module;
import com.portal.pageModel.Json;
import com.portal.pageModel.ModulePage;
import com.portal.service.ModuleServiceI;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by FGZ on 2017/11/29.
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "ModuleAction")
public class ModuleAction extends BaseAction implements ModelDriven<ModulePage> {
    private static final long serialVersionUID = 4426765458782995839L;

    private ModuleServiceI moduleService;
    @Autowired
    public void setModuleService(ModuleServiceI moduleService) {
        this.moduleService = moduleService;
    }

    ModulePage modulePage = new ModulePage();
    @Override
    public ModulePage getModel() {
        return modulePage;
    }

    /**
     * 根据typeId获取Module列表
     */
    public void getModulesByTypeId(){
        List<Module> modules = this.moduleService.getModulesByTypeId(modulePage.getTypeId());
        Json j = new Json();
        j.setSuccess(true);
        j.setMsg("查询模块名称成功");
        j.setObj(modules);
        super.writeJson(j);
    }
}
