package com.knight.dao;

import com.knight.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    // 基础操作
    Employee getEmpById(Integer id);

    int updateEmployee(Employee employee);

    int deleteEmployee(Integer id);

    int insertEmployee(Employee employee);

    // 进阶操作
    Employee getEmpByIdAndName(@Param("id") Integer id, @Param("empName") String name);

    Employee getEmpByIdAndNameAndTable(@Param("id") Integer id, @Param("empName") String name, @Param("table") String table);

    List<Employee> getAllEmps();

    Map<String, Object> getEmpByIdReturnMap(Integer id);

    /**
     * key就是记录的主键，value是记录封装好的对象
     * <p>
     * 把查询的记录的id值作为key进行封装
     */
    @MapKey("id")
    Map<String, Object> getAllEmpReturnMap();

}
