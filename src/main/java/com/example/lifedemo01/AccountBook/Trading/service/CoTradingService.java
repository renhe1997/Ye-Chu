package com.example.lifedemo01.AccountBook.Trading.service;


import com.example.lifedemo01.AccountBook.Trading.entity.CoTrading;

import java.util.List;

/**
 * (CoTrading)表服务接口
 *
 * @author makejava
 * @since 2021-02-07 17:01:40
 */
public interface CoTradingService {

    /**
     * 通过ID查询单条数据
     *
     * @param payNo 主键
     * @return 实例对象
     */
    CoTrading queryById(String payNo);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CoTrading> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param coTrading 实例对象
     * @return 实例对象
     */
    CoTrading insert(CoTrading coTrading);

    /**
     * 修改数据
     *
     * @param coTrading 实例对象
     * @return 实例对象
     */
    CoTrading update(CoTrading coTrading);

    /**
     * 通过主键删除数据
     *
     * @param payNo 主键
     * @return 是否成功
     */
    boolean deleteById(String payNo);

}