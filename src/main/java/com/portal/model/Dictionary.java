package com.portal.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by FGZ on 2017/11/22.
 */
@Entity
@Table(name = "m4_dictionary")
public class Dictionary implements Serializable{
    private static final long serialVersionUID = 1908546082948146721L;

    private Integer id;
    private String name;
    private String type;

    public Dictionary() {
    }

    public Dictionary(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
