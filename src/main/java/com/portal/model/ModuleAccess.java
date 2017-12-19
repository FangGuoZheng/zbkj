package com.portal.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FGZ on 2017/11/22.
 */
@Entity
@Table(name = "m4_module_access")
public class ModuleAccess {
    private static final long serialVersionUID = 1908546082948146723L;

    private Integer id;
    private User user;
    private Module module;
    private Date expireDate;
    private Boolean deleteflag;

    public ModuleAccess() {
        this.deleteflag = false;
    }

    public ModuleAccess(Integer id, User user, Module module, Date expireDate, Boolean deleteflag) {
        this.id = id;
        this.user = user;
        this.module = module;
        this.expireDate = expireDate;
        this.deleteflag = deleteflag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Column(name = "expiredate")
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }
}
