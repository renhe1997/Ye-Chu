package com.example.lifedemo01.Pub;

import com.example.lifedemo01.entity.BaseDict;
import com.example.lifedemo01.service.BaseDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: RenHe
 * @Date: 2021/2/25 10:26
 */
@Slf4j
@Component
public class  EnumTool {
    @Resource
    private BaseDictService baseDictService;
    public static EnumTool enumTool;
    /**
     * 根据ID查询数据字典
     * @param dictId
     * @return 枚举值Map
     */
    public static  Map<String,String> getEmunMapByDictId(String dictId){
        BaseDict baseDict = new BaseDict();
        baseDict.setDictId(dictId);
        log.debug("test"+enumTool.baseDictService.queryAllByLimit(1,10));
        List<BaseDict> resultList = enumTool.baseDictService.queryAll(baseDict);
        Map<String,String> reslutMap = new HashMap<String, String>();
        if(null != resultList && resultList.size()>0){
            for(BaseDict bd : resultList){
                reslutMap.put(bd.getDictKey(),bd.getDictValue());
            }
        }
        return reslutMap;
    }

    /**
     * 查询数据字典
     * @param dictId
     * @return
     */
    public static  Map<String,String> getEmunMapByKey(String dictId,String dictKey){

        BaseDict baseDict = enumTool.baseDictService.queryById(dictId,dictKey);
        Map<String,String> reslutMap = new HashMap<String, String>();
        reslutMap.put(baseDict.getDictKey(),baseDict.getDictValue());
        return reslutMap;
    }

    @PostConstruct
    public void init(){
        enumTool = this;
    }

}
