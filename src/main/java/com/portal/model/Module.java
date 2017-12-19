package com.portal.model;

import javax.persistence.*;

/**
 * Created by FGZ on 2017/11/22.
 */
@Entity
@Table(name = "m4_module")
public class Module {
    private static final long serialVersionUID = 1908546082948146722L;

    private Integer id;
    private String name;
    private Dictionary typeId;

    public Module() {
    }

    public Module(Integer id, String name, Dictionary typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "type_id")
    public Dictionary getTypeId() {
        return typeId;
    }

    public void setTypeId(Dictionary typeId) {
        this.typeId = typeId;
    }
}
