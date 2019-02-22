package com.chainsys.evaluationapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.Topics;

@Repository
public class AdministratorDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @param topic
	 * @throws Exception
	 * @return void
	 */
	public int addTopic(Topics topic) throws Exception {
		// TODO Auto-generated method stub

		String query = "INSERT INTO EV_TOPICS(name) VALUES(?)";
		Object[] parameters = new Object[] { topic.getName() };
		int addTopicResult = jdbcTemplate.update(query, parameters);
		return addTopicResult;
	}

	/**
	 * 
	 * @param topic
	 * @throws Exception
	 * @return void
	 */
	public int updateTopic(Topics topic) throws Exception {
		String query = "UPDATE EV_TOPICS SET name=? WHERE topicid=? ";
		Object[] parameters = new Object[] { topic.getName(), topic.getId() };
		int updateTopicResult=jdbcTemplate.update(query, parameters);
		return updateTopicResult;
	}

	/**
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */

	public List<Topics> displayTopics() throws Exception {

		String query = "SELECT topicid,name FROM EV_TOPICS";

		List<Topics> topicList = jdbcTemplate.query(query,
				(resultSet, row) -> {
					Topics topic = topicIntialization(resultSet);
					return topic;
				});
		return topicList;

	}

	/**
	 * 
	 * @param resultSet
	 * @return {@link Topics}
	 * @throws SQLException
	 */

	private Topics topicIntialization(ResultSet resultSet) throws SQLException {
		Topics topic = new Topics();
		topic.setId(resultSet.getInt("topicid"));
		topic.setName(resultSet.getString("name"));

		return topic;
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
		int addEmployeeResult=jdbcTemplate.update(query, parameters);
		return addEmployeeResult;

	}

	/**
	 * 
	 * @param topic
	 * @return void
	 */

	public int deleteTopic(Topics topic) {

		String query = "DELETE FROM EV_TOPICS WHERE name=?";
		Object[] parameters = new Object[] { topic.getId() };
		int deleteTopicResult=jdbcTemplate.update(query, parameters);
		return deleteTopicResult;
	}

/*	
	public int deleteEmployee(Employee employee) {

		String query = "DELETE FROM EV_EMPLOYEE WHERE id=?";
		Object[] parameters = new Object[] { employee.getId() };
		int deleteEmployeeResult=jdbcTemplate.update(query, parameters);
		return deleteEmployeeResult;
	}*/
	

}
