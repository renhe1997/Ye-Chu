package com.example.lifedemo01.controller;

import com.example.lifedemo01.System.entity.SysUser;
import com.example.lifedemo01.System.service.SysUserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: RenHe
 * @Date: 2021/2/4 14:26
 */
@RestController
@Slf4j
@RequestMapping(value = "/login")
public class LoginController {
    @Resource
    private SysUserService sysUserService;
    /**
     * 初始化登录页面
     * @param httpRequest
     * @return
     */
    @GetMapping(value = {"","/"})
    public ModelAndView login(HttpServletRequest httpRequest){
        return new ModelAndView("System/login");
    }


    /**
     * 验证用户名密码
     * @return
     */
    @PostMapping(value = "/doLogin")
    public String doLogin(SysUser sysUser,HttpServletRequest req){
        System.out.println("sysUser: "+sysUser);
        ModelAndView mv = new ModelAndView();
        // 组织数据
        String username = (String) req.getSession().getAttribute("username");
        String password = (String) req.getSession().getAttribute("password");
        username = "1";
        password = "1";
//        sysUser.setUserName(username);
//        sysUser.setPassword(password);

        // 查询数据库
        List<SysUser> resultList = this.sysUserService.queryAll(sysUser);
        // 判断结果并返回
        String code = "999";
        if (null != resultList && resultList.size()>0){
            SysUser user = resultList.get(0);
            code = "000";
            req.setAttribute("user",user);
            req.setAttribute("code",code);
            return "System/index";
        }else {
            req.setAttribute("code",code);
            req.setAttribute("msg","用户名密码验证错误，请重新输入");
            return "System/login";
        }
    }
    @GetMapping("selectOne")
    public SysUser selectOne(String id) {
        id = "1";
        log.info("selectOne-程序执行");
        return this.sysUserService.queryById(id);
    }
}
