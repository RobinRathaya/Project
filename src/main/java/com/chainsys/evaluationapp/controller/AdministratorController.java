package com.chainsys.evaluationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.evaluationapp.dao.EmployeeDAO;
import com.chainsys.evaluationapp.dao.EmployeeTopicsDAO;
import com.chainsys.evaluationapp.dao.TopicsDAO;
import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.EmployeeTopics;
import com.chainsys.evaluationapp.model.Topics;
import com.chainsys.evaluationapp.services.Services;

@CrossOrigin
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	EmployeeDAO employeeDAO;
	@Autowired
	TopicsDAO topicDAO;
	@Autowired
	EmployeeTopicsDAO employeeTopicsDAO;
	@Autowired
	Services services;

	@PostMapping("/addUser")
	public int registeration(@RequestParam("empid") int empid,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password) throws Exception {
		Employee employee = new Employee();
		employee.setId(empid);
		employee.setName(name);
		employee.setEmail(email);
		employee.setPassword(password);
		int noOfRows = employeeDAO.addEmployee(employee);
		return noOfRows;

	}

	@PostMapping("/addTopic")
	public int insertTopic(@RequestParam("topicName") String topicName)
			throws Exception {
		Topics topic = new Topics();

		topic.setName(topicName);
		int noOfRows = topicDAO.addTopic(topic);
		return noOfRows;
	}

	@GetMapping("/dispayUsers")
	public List<Employee> displayUsers() {

		return employeeDAO.fetchUsers();
	}

	@GetMapping("/displayEvaluation")
	public List<EmployeeTopics> displayEvaluation() {
		List<EmployeeTopics> employeeTopicsList = employeeTopicsDAO
				.searchEvaluation();

		return services.fetchUserTopicsDetails(employeeTopicsList);
	}

}
