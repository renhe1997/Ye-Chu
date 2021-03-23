package com.example.lifedemo01.service.impl;

import com.example.lifedemo01.entity.BaseDict;
import com.example.lifedemo01.Dao.BaseDictDao;
import com.example.lifedemo01.service.BaseDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseDict)表服务实现类
 *
 * @author makejava
 * @since 2020-08-13 17:15:49
 */
@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {
    @Resource
    private BaseDictDao baseDictDao;

    /**
     * 动态条件查询数据
     * @param baseDict
     * @return对象列表
     */
    @Override
    public List<BaseDict> queryAll(BaseDict baseDict) {
        return this.baseDictDao.queryAll(baseDict);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    @Override
    public BaseDict queryById(String dictId,String dictKey) {
        return this.baseDictDao.queryById(dictId,dictKey);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseDict> queryAllByLimit(int offset, int limit) {
        return this.baseDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseDict 实例对象
     * @return 实例对象
     */
    @Override
    public BaseDict insert(BaseDict baseDict) {
        this.baseDictDao.insert(baseDict);
        return baseDict;
    }

    /**
     * 修改数据
     *
     * @param baseDict 实例对象
     * @return 实例对象
     */
    @Override
    public BaseDict update(BaseDict baseDict) {
        this.baseDictDao.update(baseDict);
        return this.queryById(baseDict.getDictId(),baseDict.getDictKey());
    }

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String dictId) {
        return this.baseDictDao.deleteById(dictId) > 0;
    }
}