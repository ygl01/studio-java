package com.kuang.dao;

import com.kuang.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ygl
 * @description 部门dao
 * @date 2020/10/26 17:27
 */
@Repository
public class DepartmentDao {

    //模拟数据库
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer, Department>();
        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"市场部"));
        departments.put(103,new Department(103,"教研部"));
        departments.put(104,new Department(104,"运营部"));
        departments.put(105,new Department(105,"后勤部"));
    }

    //获取部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //根据id获取到部门信息
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }

}
