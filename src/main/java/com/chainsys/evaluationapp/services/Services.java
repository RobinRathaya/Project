package com.chainsys.evaluationapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.evaluationapp.dao.EmployeeDAO;
import com.chainsys.evaluationapp.dao.EmployeeTopicsDAO;
import com.chainsys.evaluationapp.dao.StatusDAO;
import com.chainsys.evaluationapp.dao.TopicsDAO;
import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.EmployeeTopics;
import com.chainsys.evaluationapp.model.Topics;

@Service
public class Services {
	@Autowired
	public EmployeeTopicsDAO employeeTopicsDAO;
	@Autowired
	public TopicsDAO topicsDAO;
	@Autowired
	public StatusDAO statusDAO;
	@Autowired
	public EmployeeDAO employeeDAO;

	public List<EmployeeTopics> fetchUserDetails(Employee employee)
			throws Exception {

		List<EmployeeTopics> employeeEvaluationDetails = employeeTopicsDAO
				.searchEvaluationById(employee);

		employeeEvaluationDetails.forEach(employeeDetail -> {

			employeeDetail.setTopic(topicsDAO.searchTopicName(employeeDetail
					.getTopic().getId()));
			employeeDetail.setStatus(statusDAO.searchStatusName(employeeDetail
					.getStatus().getId()));

		});

		return employeeEvaluationDetails;

	}

	public Topics fetchTopicId(Topics topic) {
		System.out.println("fetchTopic " + topic.getName());
		Topics fetchedTopic = topicsDAO.searchTopicId(topic.getName());
		System.out.println("Fetched id" + fetchedTopic.getId());
		return fetchedTopic;
	}

	public List<EmployeeTopics> fetchUserTopicsDetails(
			List<EmployeeTopics> employeeTopicsList) {

		employeeTopicsList.forEach(employeeDetail -> {
			employeeDetail.setEmployee(employeeDAO
					.searchEmployeeName(employeeDetail.getEmployee()));
			employeeDetail.setTopic(topicsDAO.searchTopicName(employeeDetail
					.getTopic().getId()));
			employeeDetail.setStatus(statusDAO.searchStatusName(employeeDetail
					.getStatus().getId()));
		});

		return employeeTopicsList;
	}
}
