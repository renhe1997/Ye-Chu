package com.example.lifedemo01.controller;

import com.example.lifedemo01.entity.BaseDict;
import com.example.lifedemo01.service.BaseDictService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * (BaseDict)表控制层
 *
 * @author renhe
 * @since 2020-08-13 17:15:56
 */
@RestController
@RequestMapping("baseDict")
public class BaseDictController {
    private static Logger log = LoggerFactory.getLogger(BaseDictController.class);
    /**
     * 服务对象
     */
    @Resource
    private BaseDictService baseDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseDict selectOne(String id) {
        id = "COST_TYPE";
        String key = "0";
        log.info("selectOne-程序执行");
        return this.baseDictService.queryById(id,key);
    }
    @GetMapping("selectAll")
    public List<BaseDict> selectAll(){
        System.out.println("selectAll-查询所有");
        return this.baseDictService.queryAllByLimit(1,10);
    }

}