package com.chainsys.evaluationapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.evaluationapp.model.Topics;

@Repository
public class TopicsDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Topics searchTopicName(int topicId) {

		String query = "SELECT t.name FROM EV_TOPICS t WHERE t.topicid=?";
		Object[] parameters = new Object[] { topicId };

		Topics topic = jdbcTemplate.queryForObject(query, parameters, (
				resultSet, row) -> {
			Topics topicNames = topicNameIntialization(resultSet);

			return topicNames;
		});
		return topic;

	}

	private Topics topicNameIntialization(ResultSet resultSet)
			throws SQLException {

		Topics topic = new Topics();
		topic.setName(resultSet.getString("t.name"));

		return topic;
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

}
