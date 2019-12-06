package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Role;

import java.util.List;

//角色权限管理接口
public interface RoleManagementService {
    //查询所有的角色
    public List<Role> findAll();
    //分页查询所有角色
    PageResult findPage(QueryPageBean queryPageBean);
     //添加角色的方法
      public void add(Role role, Integer[] checkitemIds);
     //根据id查询单个角色
    Role findById(Integer id);
     //修改角色信息
    void edit(Role role, Integer[] checkitemIds, Integer[] menuIds);
    //根据id删除角色信息
    void deleteOne(Integer id);

    Long findUserById(Integer id);


}
