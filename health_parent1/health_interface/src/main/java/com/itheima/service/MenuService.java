package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;

import java.util.List;

public interface MenuService {
    PageResult findPage(QueryPageBean queryPageBean);

    List<Menu> findAllMenu();

    List<Integer> findMenusByRoleId(Integer id);

    PageResult findMenuPage(QueryPageBean queryPageBean);

    void addMenu(Menu menu);

    Menu findMenuById(Integer id);

    void editMenu(Menu menu);

    void delete(Integer id);

    List<Integer> findMenuIdsById(Integer id);
}
