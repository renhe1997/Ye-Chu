package com.example.lifedemo01.Dao;

import com.example.lifedemo01.entity.BaseDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (BaseDict)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-13 17:15:49
 */
@Mapper
public interface BaseDictDao {



    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    BaseDict queryById(String dictId,String dictKey);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseDict> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param baseDict 实例对象
     * @return 对象列表
     */
    List<BaseDict> queryAll(BaseDict baseDict);

    /**
     * 新增数据
     *
     * @param baseDict 实例对象
     * @return 影响行数
     */
    int insert(BaseDict baseDict);

    /**
     * 修改数据
     *
     * @param baseDict 实例对象
     * @return 影响行数
     */
    int update(BaseDict baseDict);

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 影响行数
     */
    int deleteById(String dictId);

}