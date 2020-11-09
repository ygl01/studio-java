package com.kuang.controller;

import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Collection;

/**
 * @author ygl
 * @description
 * @date 2020/10/27 19:01
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {

        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //修改页面
    @GetMapping("/emp/{id}")
    public String update(@PathVariable Integer id, Model model) {

        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        System.out.println("1111：" + employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    //添加修改内容
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        Integer id = employee.getId();
        employeeDao.save(employee);
        employee.setId(id);
        return "redirect:/emps";
    }

    /**
     * @author ygl
     * @date 2020-11-02 19:25
     */
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") int id) {
        System.out.println("删除的ID：" + id);
        employeeDao.deleteEmployeeById(id);
        return "redirect:/emps";
    }
}
