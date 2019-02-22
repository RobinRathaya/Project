package com.chainsys.evaluationapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.evaluationapp.model.Employee;

@Repository
public class EmployeeDAO {
@Autowired
JdbcTemplate jdbcTemplate;

public List<Employee> fetchUsers()
{
	String query="SELECT id,name FROM EV_EMPLOYEE";
	List<Employee> userDetails=jdbcTemplate.query(query, (resultSet,row)->{
		
		Employee userInfo=EmployeeDetailInitialization(resultSet);
		return userInfo;
		
	});
	return userDetails;
}

private Employee EmployeeDetailInitialization(ResultSet resultSet) throws SQLException {
	// TODO Auto-generated method stub
	Employee employee=new Employee();
	employee.setId(resultSet.getInt("id"));
	employee.setName(resultSet.getString("name"));
	return employee;
}
}
