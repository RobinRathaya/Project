package com.chainsys.evaluationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.EmployeeTopics;
import com.chainsys.evaluationapp.services.Services;

@CrossOrigin
@RestController
@RequestMapping("/HR")
public class HrController {
	@Autowired
	Services services;

	@PostMapping("/searchUser")
	public List<EmployeeTopics> searchUserDetails(
			@RequestParam("empid") int empid) throws Exception {

		Employee employee = new Employee();
		employee.setId(empid);
		List<EmployeeTopics> userDetails = services.fetchUserDetails(employee);

		return userDetails;
	}

}
