package com.chainsys.evaluationapp.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.EmployeeTopics;
import com.chainsys.evaluationapp.services.Services;

@Repository
public class AuthenticationDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	Services services;

	/**
	 * 
	 * @param resultSet
	 * @return {@link Employee}
	 * @throws SQLException
	 */

	private Employee employeeIntialization(ResultSet resultSet)
			throws SQLException {
		Employee employee = new Employee();
		employee.setId(resultSet.getInt("id"));
		employee.setName(resultSet.getString("name"));
		return employee;
	}

	/**
	 * 
	 * @param employee
	 * @return {@link Employee}
	 * @throws Exception 
	 */

	public List<EmployeeTopics> loginValidation(Employee employee) throws Exception {
		
		String query = "SELECT id,name,email,password FROM EV_EMPLOYEE WHERE email= ? AND password=? ";

		Object[] parameters = new Object[] { employee.getEmail(),
				employee.getPassword() };

			Employee employeeDetail = jdbcTemplate
					.queryForObject(
							query,
							parameters,
							(resultSet, row) -> {
								Employee userInfo = employeeIntialization(resultSet);
								return userInfo;
							});
			System.out.println(employeeDetail.getId()+"login validation");
			List<EmployeeTopics> userDetails;
			userDetails=services.fetchUserDetails(employeeDetail);
			return userDetails;

	}

	/**
	 * 
	 * @param employee
	 * @throws Exception
	 * @return Void
	 */
	public int addEmployee(Employee employee) throws Exception {

		String query = "INSERT INTO EV_EMPLOYEE values(?,?,?,?)";

		Object[] parameters = new Object[] { employee.getId(),
				employee.getName(), employee.getEmail(), employee.getPassword() };

		int insertResult = jdbcTemplate.update(query, parameters);
		return insertResult;

	}

	public int resetPassword(Employee employee) {
		String query = "UPDATE EV_EMPLOYEE SET password=? WHERE id=?";
		Object[] parameters = new Object[] { employee.getPassword(),
				employee.getId() };
		int resetPasswordResult = jdbcTemplate.update(query, parameters);
		return resetPasswordResult;
	}
	
	public int updateName(Employee employee) {
		String query = "UPDATE EV_EMPLOYEE SET name=? WHERE id=?";
		Object[] parameters = new Object[] { employee.getName(),
				employee.getId() };
		int updateNameResult = jdbcTemplate.update(query, parameters);
		return updateNameResult;
	}

}
