package ynu.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ynu.myblog.entity.SysUser;
import ynu.myblog.service.SysUserService;

@RestController
public class SysUserController {


    @Autowired
    SysUserService sysUserService;

    @PostMapping("/login")
    public JSONObject login(@RequestBody SysUser sysUser){
        System.out.println(sysUser.getUsername());
        System.out.println(sysUser.getPassword());
        return sysUserService.sysuserlogin(sysUser.getUsername(),sysUser.getPassword());
    }

    @GetMapping("/logout")
    public JSONObject logout(@Param("userid") int userid){
        return sysUserService.sysuserlogout(userid);
    }

}
