package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ygl
 * @description 员工表
 * @date 2020/10/26 17:38
 */
@Repository
public class EmployeeDao {
    //引入部门dao
    @Autowired
    private DepartmentDao departmentDao;
    //模拟员工数据库
    public static Map<Integer, Employee> employees = null;

    static {

        employees = new HashMap<>();

        employees.put(1001, new Employee(1001, "aa", "A15252554@qq.com", 1, new Department(101, "后勤部")));
        employees.put(1002, new Employee(1002, "bb", "B15252554@qq.com", 0, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "cc", "C15252554@qq.com", 1, new Department(103, "教学部")));
        employees.put(1004, new Employee(1004, "dd", "D15252554@qq.com", 0, new Department(104, "教研部")));
        employees.put(1005, new Employee(1005, "ee", "E15252554@qq.com", 0, new Department(105, "后勤部")));
    }

    //主键自增
    public static Integer initId = 1006;

    //新增一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId);
            initId++;
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询全部员工
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //查询一个员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //删除员工
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }
}
