package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleManagementDao {
    public List<Role> findAll();

    Page<Role> findByQueryString(String queryString);

    public void add(Role role);

    void addRoleAndPermission(@Param("rid") Integer rid, @Param("pid") Integer pId);

    public Role findByid(Integer id);

    void deleteAllpermissionIds(Integer id);

    void updateRole(Role role);

    void deleteRole(Integer id);

    Long findUserById(Integer id);

    void deleteAllMenu(Integer id);
}
