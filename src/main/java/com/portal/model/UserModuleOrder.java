package com.portal.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FGZ on 2017/11/22.
 */
@Entity
@Table(name = "m4_user_module_order")
public class UserModuleOrder {
    private static final long serialVersionUID = 1908546082948146724L;

    private Integer Id;
    private User user;
    private Module module;
    private Staff opeUser;
    private Float amount;
    private Date time;
    private String description;
    private Boolean deleteflag;

    public UserModuleOrder() {
        this.deleteflag = false;
    }

    public UserModuleOrder(Integer id, User user, Module module, Staff opeUser, Float amount, Date time, String description, Boolean deleteflag) {
        Id = id;
        this.user = user;
        this.module = module;
        this.opeUser = opeUser;
        this.amount = amount;
        this.time = time;
        this.description = description;
        this.deleteflag = deleteflag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "module_id")
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @ManyToOne
    @JoinColumn(name = "ope_user_id")
    public Staff getOpeUser() {
        return opeUser;
    }

    public void setOpeUser(Staff opeUser) {
        this.opeUser = opeUser;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }
}
