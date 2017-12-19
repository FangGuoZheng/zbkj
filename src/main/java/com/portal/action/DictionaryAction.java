package com.portal.action;

import com.opensymphony.xwork2.ModelDriven;
import com.portal.model.Dictionary;
import com.portal.pageModel.Json;
import com.portal.service.DictionaryServiceI;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by FGZ on 2017/12/6.
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "DictionaryAction")
public class DictionaryAction  extends BaseAction implements ModelDriven<Dictionary> {
    private static final long serialVersionUID = 4426765458783654839L;

    private DictionaryServiceI dictionaryService;
    @Autowired
    public void setDictionaryService(DictionaryServiceI dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    Dictionary dictionary = new Dictionary();
    @Override
    public Dictionary getModel() {
        return dictionary;
    }

    /**
     * 获取Dictionary的列表数据
     */
    public void getAll(){
        List<Dictionary> dictionaries = this.dictionaryService.getAll();
        Json j = new Json();
        j.setSuccess(true);
        j.setMsg("查询模块类型成功");
        j.setObj(dictionaries);
        super.writeJson(j);
    }
}
