package com.chainsys.evaluationapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.evaluationapp.dao.EmployeeTopicsDAO;
import com.chainsys.evaluationapp.dao.StatusDAO;
import com.chainsys.evaluationapp.dao.TopicsDAO;
import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.EmployeeTopics;

@Service
public class Services {
	@Autowired
	EmployeeTopicsDAO employeeTopicsDAO;
	@Autowired
	TopicsDAO topicsDAO;
	@Autowired
	StatusDAO statusDAO;

	public List<EmployeeTopics> fetchUserDetails(Employee employee) throws Exception {
		
		List<EmployeeTopics> employeeEvaluationDetails = employeeTopicsDAO
				.searchEvaluationById(employee);
		
		employeeEvaluationDetails.forEach(employeeDetail -> {

			employeeDetail.setTopic(topicsDAO.searchTopicName(employeeDetail
					.getTopic().getId()));
	

		});
		
		employeeEvaluationDetails.forEach(employeeDetail -> {

			employeeDetail.setStatus(statusDAO.searchStatusName(employeeDetail
					.getStatus().getId()));

		});
		

		return employeeEvaluationDetails;

	}
}
