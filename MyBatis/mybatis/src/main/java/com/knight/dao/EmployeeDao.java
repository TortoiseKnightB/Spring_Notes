package com.knight.dao;

import com.knight.bean.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeDao {

    Employee getEmpById(Integer id);

    int updateEmployee(Employee employee);

    int deleteEmployee(Integer id);

    int insertEmployee(Employee employee);


    Employee getEmpByIdAndName(@Param("id") Integer id, @Param("empName") String name);

    Employee getEmpByIdAndNameAndTable(@Param("id") Integer id, @Param("empName") String name, @Param("table") String table);


}
