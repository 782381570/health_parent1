package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.dao.RoleManagementDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Role;
import com.itheima.service.RoleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass =RoleManagementService.class )
@Transactional
public class RoleManagementServiceImpl implements RoleManagementService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleManagementDao roleManagementDao;
   //查询所有的角色
    public List<Role> findAll() {
        return roleManagementDao.findAll();
    }

    //分页查询
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //调用mybatis分页助手
        PageHelper.startPage(currentPage,pageSize);
        //调用方法完成条件查询;
        Page<Role> groupPage = roleManagementDao.findByQueryString(queryString);
        List<Role> result = groupPage.getResult();
        long total = groupPage.getTotal();
        return new PageResult(total,result);
    }

    //添加角色
    public void add(Role role, Integer[] checkitemIds) {
        roleManagementDao.add(role);
        if (checkitemIds != null && checkitemIds.length > 0){
            for (Integer pId : checkitemIds) {
                roleManagementDao.addRoleAndPermission(role.getId(),pId);
            }
        }
    }

    //根据id查询单个角色用于数据回显
    public Role findById(Integer id) {
        Role role = roleManagementDao.findByid(id);
        return role;
    }

    //编辑角色信息
    public void edit(Role role, Integer[] permissionIds, Integer[] menuIds) {

        //根据检查组id删除之前所有的检查项
        roleManagementDao.deleteAllpermissionIds(role.getId());
        //根据检查组id删除之前所有的
        menuDao.delMenuIds(role.getId());
        //修改检查组基本信息
        roleManagementDao.updateRole(role);
        //重新给中间表赋值
        if (permissionIds!=null && permissionIds.length > 0) {
            for (Integer permissionId : permissionIds) {
                roleManagementDao.addRoleAndPermission(role.getId(), permissionId);
            }
        }

        Map<String,Object> map = new HashMap<>();
        if (menuIds!=null && menuIds.length > 0) {
            map.put("roleId",role.getId());
            map.put("menuIds",menuIds);
            menuDao.addRoleAndMenu(map);
        }
    }

    //根据id删除角色
    public void deleteOne(Integer id) {
        //根据id删除中间表
        roleManagementDao.deleteAllpermissionIds(id);
        roleManagementDao.deleteAllMenu(id);
        //根据id删除角色基本信息
        roleManagementDao.deleteRole(id);
    }

    @Override
    public Long findUserById(Integer id) {
        return roleManagementDao.findUserById(id);
    }
}
