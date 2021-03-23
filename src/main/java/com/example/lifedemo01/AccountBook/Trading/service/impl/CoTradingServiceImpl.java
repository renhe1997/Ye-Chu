package com.example.lifedemo01.AccountBook.Trading.service.impl;


import com.example.lifedemo01.AccountBook.Trading.dao.CoTradingDao;
import com.example.lifedemo01.AccountBook.Trading.entity.CoTrading;
import com.example.lifedemo01.AccountBook.Trading.service.CoTradingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CoTrading)表服务实现类
 *
 * @author makejava
 * @since 2021-02-07 17:01:40
 */
@Service("coTradingService")
public class CoTradingServiceImpl implements CoTradingService {
    @Resource
    private CoTradingDao coTradingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param payNo 主键
     * @return 实例对象
     */
    @Override
    public CoTrading queryById(String payNo) {
        return this.coTradingDao.queryById(payNo);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CoTrading> queryAllByLimit(int offset, int limit) {
        return this.coTradingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param coTrading 实例对象
     * @return 实例对象
     */
    @Override
    public CoTrading insert(CoTrading coTrading) {
        this.coTradingDao.insert(coTrading);
        return coTrading;
    }

    /**
     * 修改数据
     *
     * @param coTrading 实例对象
     * @return 实例对象
     */
    @Override
    public CoTrading update(CoTrading coTrading) {
        this.coTradingDao.update(coTrading);
        return this.queryById(coTrading.getPayNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param payNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String payNo) {
        return this.coTradingDao.deleteById(payNo) > 0;
    }
}