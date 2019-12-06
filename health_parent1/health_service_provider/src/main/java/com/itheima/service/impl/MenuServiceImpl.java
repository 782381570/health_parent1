package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Role;
import com.itheima.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    //分页查询角色
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Role> page = menuDao.findPage(queryPageBean.getQueryString());
        List<Role> rows = page.getResult();
        long total = page.getTotal();
        return new PageResult(total,rows);
    }


    //查询所有菜单
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }


    //查询角色所拥有的菜单
    public List<Integer> findMenusByRoleId(Integer id) {
        return menuDao.findMenusByRoleId(id);
    }


    //菜单分页查询
    public PageResult findMenuPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Menu> page = menuDao.findMenuPage(queryPageBean.getQueryString());
        List<Menu> rows = page.getResult();
        long total = page.getTotal();
        return new PageResult(total,rows);
    }


    //新增菜单
    public void addMenu(Menu menu) {
        menuDao.addMenu(menu);
    }

    //根据id查询菜单
    public Menu findMenuById(Integer id) {
        return menuDao.findMenuById(id);
    }


    //编辑菜单
    public void editMenu(Menu menu) {
         menuDao.editMenu(menu);
    }


    //删除菜单
    public void delete(Integer id) {
        List<Integer> roles = menuDao.findMenusByMenuId(id);
        if(roles!=null&&roles.size() > 0){
            return;
        }
        menuDao.delete(id);
    }

    @Override
    public List<Integer> findMenuIdsById(Integer id) {
        return menuDao.findMenuIdsById(id);
    }
}
