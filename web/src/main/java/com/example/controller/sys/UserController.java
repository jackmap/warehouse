package com.example.controller.sys;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.common.RequestFilter;
import com.example.controller.sys.entity.SysUser;
import com.example.controller.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return userService.findAll();
    }

    @PostMapping("/findPageFilter")
    public JsonResult findPage(@RequestBody RequestFilter requestFilter){
        return userService.findUserPage(requestFilter);
    }

    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return userService.findUserPage(page,limit);
    }

    @PostMapping("/add")
    public JsonResult addUser(SysUser user){
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    public JsonResult editUser(SysUser user){
        return userService.editUser(user);
    }


    @PostMapping("/delete/{uid}")
    public JsonResult deleteUser(@PathVariable Integer uid){
        return userService.deleteUser(uid);
    }

    @PostMapping("/ChangState/{uid}")
    public JsonResult ChangState(@PathVariable Integer uid){
        return userService.ChangUserState(uid);
    }

}