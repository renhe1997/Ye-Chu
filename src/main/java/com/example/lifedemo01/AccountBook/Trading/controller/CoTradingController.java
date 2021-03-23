package com.example.lifedemo01.AccountBook.Trading.controller;


import com.example.lifedemo01.AccountBook.CalculateCmd;
import com.example.lifedemo01.AccountBook.Trading.entity.CoTrading;
import com.example.lifedemo01.AccountBook.Trading.service.CoTradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CoTrading)表控制层
 *
 * @author makejava
 * @since 2021-02-07 17:01:41
 */
@RestController
@Slf4j
@RequestMapping("coTrading")
public class CoTradingController {
    /**
     * 服务对象
     */
    @Resource
    private CoTradingService coTradingService;
    @Resource
    private CalculateCmd calculateCmd;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public CoTrading selectOne(String id) {
        id = "350395466237607936";
        return this.coTradingService.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @PostMapping("selectAll")
    public List<CoTrading> queryAllByLimit(int offset, int limit){
        return this.coTradingService.queryAllByLimit(offset,limit);
    }

    /**
     * 新增数据
     *
     * @param coTrading 实例对象
     * @return 实例对象
     */
    @PostMapping("insert")
    public CoTrading insert(CoTrading coTrading){
        return this.coTradingService.insert(coTrading);
    }

    /**
     * 修改数据
     *
     * @param coTrading 实例对象
     * @return 实例对象
     */
    @PostMapping("update")
    public CoTrading update(CoTrading coTrading){
        return this.coTradingService.update(coTrading);
    }

    /**
     * 通过主键删除数据
     *
     * @param payNo 主键
     * @return 是否成功
     */
    @PostMapping("deleteById")
    public boolean deleteById(String payNo){
        return this.coTradingService.deleteById(payNo);
    }

    @GetMapping("import")
    public List<CoTrading> importBYExcle(String id) {
        this.calculateCmd.importAccount();
        return this.coTradingService.queryAllByLimit(1,10);
    }



}