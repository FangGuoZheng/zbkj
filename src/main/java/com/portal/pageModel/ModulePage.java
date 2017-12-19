package com.portal.pageModel;

/**
 * Created by FGZ on 2017/11/29.
 */
public class ModulePage {
    private Integer id;
    private String name;
    private Integer typeId;

    public ModulePage() {
    }

    public ModulePage(Integer id, String name, Integer typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
