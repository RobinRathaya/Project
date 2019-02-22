package com.chainsys.evaluationapp.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.evaluationapp.dao.EmployeeDAO;
import com.chainsys.evaluationapp.dao.TopicsDAO;
import com.chainsys.evaluationapp.model.*;

public class Validate {

	public static boolean employeeValidation(Employee employee) {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		return false;

	}

	public static boolean topicValidation(Topics topic) {
		TopicsDAO topicsDAO = new TopicsDAO();
		boolean isExist = false;
		Topics topicPresent = topicsDAO.searchTopicId(topic.getName());
		if (topicPresent.getId() > 0) {
			isExist = true;
		} else {
			isExist = false;
		}
		return isExist;
	}

}
