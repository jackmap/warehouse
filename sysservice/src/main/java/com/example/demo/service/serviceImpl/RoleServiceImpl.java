package com.example.demo.service.serviceImpl;

import com.example.demo.common.JsonResult;
import com.example.demo.common.RequestFilter;
import com.example.demo.dao.RoleDao;
import com.example.demo.entity.SysRole;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
        return null;
    }

    @Override
    public JsonResult editRole(SysRole role) {
        return null;
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
