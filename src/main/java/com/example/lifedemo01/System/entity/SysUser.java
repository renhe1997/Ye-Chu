package com.example.lifedemo01.System.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2020-11-25 10:24:34
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 683596065334404951L;
    /**
    * 用户编码
    */
    private String userId;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 登录密码
    */
    private String password;
    /**
    * 头像
    */
    private String portrait;
    /**
    * 是否在使用
    */
    private String isUse;
    /**
    * 注册时间
    */
    private String crtTime;
    /**
    * 最后登录时间
    */
    private String lastLoginDate;
    /**
    * 角色编码
    */
    private String roleId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}