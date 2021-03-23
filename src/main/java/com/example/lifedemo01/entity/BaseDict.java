package com.example.lifedemo01.entity;

import java.io.Serializable;

/**
 * (BaseDict)实体类
 *
 * @author makejava
 * @since 2020-08-13 17:15:42
 */
public class BaseDict implements Serializable {
    private static final long serialVersionUID = 388059678096074090L;
    /**
    * 备注编号
    */
    private String dictId;
    /**
    * 键
    */
    private String dictKey;
    /**
    * 值
    */
    private String dictValue;
    /**
    * 描述
    */
    private String dictDesc;


    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

}