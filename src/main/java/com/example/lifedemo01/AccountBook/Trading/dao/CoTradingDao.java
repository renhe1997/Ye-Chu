package com.example.lifedemo01.AccountBook.Trading.dao;

import com.example.lifedemo01.AccountBook.Trading.entity.CoTrading;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (CoTrading)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-07 17:01:39
 */
@Mapper
public interface CoTradingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param payNo 主键
     * @return 实例对象
     */
    CoTrading queryById(String payNo);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CoTrading> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param coTrading 实例对象
     * @return 对象列表
     */
    List<CoTrading> queryAll(CoTrading coTrading);

    /**
     * 新增数据
     *
     * @param coTrading 实例对象
     * @return 影响行数
     */
    int insert(CoTrading coTrading);

    /**
     * 修改数据
     *
     * @param coTrading 实例对象
     * @return 影响行数
     */
    int update(CoTrading coTrading);

    /**
     * 通过主键删除数据
     *
     * @param payNo 主键
     * @return 影响行数
     */
    int deleteById(String payNo);

}