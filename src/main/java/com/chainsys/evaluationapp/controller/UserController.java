package com.chainsys.evaluationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.evaluationapp.dao.AuthenticationDAO;
import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.EmployeeTopics;

@RestController
@RequestMapping("/evaluation")
public class UserController {

	@Autowired
	public AuthenticationDAO authenticationDAO;

	@PostMapping("/login")
	public List<EmployeeTopics> login(@RequestParam("email") String email,
			@RequestParam("password") String password) throws Exception {

		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setPassword(password);
		List<EmployeeTopics> userDetails = authenticationDAO
				.loginValidation(employee);
		return userDetails;
	}
}
