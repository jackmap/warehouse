package com.example.sys.service.serviceImpl;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.dao.RoleDao;
import com.example.sys.entity.SysRole;
import com.example.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao  roleDao;

    @Override
    public JsonResult findAllRole() {
       List<SysRole> list= roleDao.findAll();
       if (list!=null){
           return new JsonResult(0,list,"查询成功");
       }else {
           return new JsonResult(1,"查询失败");
       }
    }


    @Override
    public JsonResult addRole(SysRole role) {
        SysRole sysrole=roleDao.findByRoleName(role.getRoleName());
        if(sysrole==null){
            role.setCreateTime(new Date());
            roleDao.save(role);
            return  new JsonResult(0,"角色保存成功！");
        }else{
            return  new JsonResult(1,"角色名称已存在，请更换角色名称！");
        }
    }

    @Override
    public JsonResult editRole(SysRole role) {
        SysRole sysrole=roleDao.findById(role.getRid()).get();
        if(sysrole!=null){
            sysrole.setRoleName(role.getRoleName());
            sysrole.setAvailable(role.getAvailable());
            sysrole.setDescription(role.getDescription());
            sysrole.setUpdateTime(new Date());
            roleDao.save(sysrole);
            return  new JsonResult(0,"修改角色成功！");
        }else{
            return  new JsonResult(0,"角色不存在，请查询你的角色信息是否存在！");
        }
    }

    @Override
    public JsonResult findPage(int page, int limit) {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"rid");
        Page<SysRole> pages = roleDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public JsonResult findPageFilter(RequestFilter requestFilter) {
        Pageable pageable = (Pageable) new PageRequest(requestFilter.getPage()-1,requestFilter.getLimit(), Sort.Direction.ASC,"rid");
        Page<SysRole> pages = roleDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }
}