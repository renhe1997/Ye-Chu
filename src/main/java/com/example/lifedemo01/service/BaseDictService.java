package com.example.lifedemo01.service;

import com.example.lifedemo01.entity.BaseDict;
import java.util.List;

/**
 * (BaseDict)表服务接口
 *
 * @author makejava
 * @since 2020-08-13 17:15:49
 */
public interface BaseDictService {


    /**
     * 动态条件查询数据
     * @param baseDict
     * @return对象列表
     */
    List<BaseDict> queryAll(BaseDict baseDict);
    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    BaseDict queryById(String dictId,String dictKey);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseDict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param baseDict 实例对象
     * @return 实例对象
     */
    BaseDict insert(BaseDict baseDict);

    /**
     * 修改数据
     *
     * @param baseDict 实例对象
     * @return 实例对象
     */
    BaseDict update(BaseDict baseDict);

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    boolean deleteById(String dictId);

}