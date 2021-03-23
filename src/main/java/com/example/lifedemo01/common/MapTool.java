package com.example.lifedemo01.common;

import java.util.*;

/**
 * @Author: RenHe
 * @Date: 2021/2/25 11:39
 */
public class MapTool {
    public static Object getKey(Map map, Object value){
        Object val = null;
        for(Object key: map.keySet()){
            if(map.get(key).equals(value)){
                val = key;
                break;
            }
        }
        return val;
    }
}
