package cn.edu.uestc.rms.dao;

import cn.edu.uestc.rms.model.Employee;
import cn.edu.uestc.rms.query.EmployeeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    int add(@Param("employee") Employee employee);

    int updateNum(@Param("employee") Employee employee);

    List<Employee> query(@Param("query") EmployeeQuery query);

    List<Employee> queryMinNum(@Param("query") EmployeeQuery query);
}
