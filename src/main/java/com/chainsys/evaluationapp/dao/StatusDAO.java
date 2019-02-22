package com.chainsys.evaluationapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.evaluationapp.model.Status;

@Repository
public class StatusDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Status searchStatusName(int statusId) {
		String query = "SELECT s.name FROM EV_STATUS s WHERE s.id=?";
		Object[] parameters = new Object[] { statusId };
		Status statusName = jdbcTemplate.queryForObject(query, parameters, (
				resultSet, row) -> {

			Status name = statusNameIntailization(resultSet);
			return name;
		});

		return statusName;
	}

	private Status statusNameIntailization(ResultSet resultSet)
			throws SQLException {
		// TODO Auto-generated method stub
		Status name = new Status();
		name.setName(resultSet.getString("s.name"));
		return name;
	}

}
