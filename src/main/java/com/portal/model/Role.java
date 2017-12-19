package com.portal.model;

import javax.persistence.*;

/**
 * Created by FGZ on 2017/10/9.
 */
@Entity
@Table(name = "m4_role")
public class Role implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1908545072948146720L;
    // Fields
    private Integer id;
    private String name;
    private String roleName;

    public Role() {
    }

    public Role(Integer id, String name, String roleName) {
        this.id = id;
        this.name = name;
        this.roleName = roleName;
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

    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
