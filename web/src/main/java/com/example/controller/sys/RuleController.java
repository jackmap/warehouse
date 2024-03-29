package com.example.controller.sys;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.common.RequestFilter;
import com.example.controller.sys.entity.SysPermission;
import com.example.controller.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/rule")
public class RuleController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return permissionService.findAll();
    }

    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return permissionService.findRulePage(page,limit);
    }

    @PostMapping("/findMenu")
    public JsonResult findMenu(){
        return permissionService.findMenuRule();
    }

    @PostMapping("/findPageFilter")
    public JsonResult findPageFilter(@RequestBody RequestFilter requestFilter){
        return permissionService.findRulePageFilter(requestFilter);
    }

    @PostMapping("/add")
    public JsonResult addPermission(@RequestBody SysPermission  permission){
        return permissionService.addPermission(permission);
    }

    @PostMapping("/edit")
    public JsonResult editPermission(@RequestBody SysPermission permission){
        return permissionService.editPermission(permission);
    }

}
