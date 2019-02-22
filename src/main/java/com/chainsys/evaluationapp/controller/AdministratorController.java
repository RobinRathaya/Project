package com.chainsys.evaluationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.evaluationapp.dao.AdministratorDAO;
import com.chainsys.evaluationapp.dao.EmployeeDAO;
import com.chainsys.evaluationapp.model.Employee;
import com.chainsys.evaluationapp.model.Topics;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	AdministratorDAO administratorDAO;
	@Autowired
	EmployeeDAO employeeDAO;

	@PostMapping("/addUser")
	public int registeration(@RequestParam("empid") int empid,
			@RequestParam("name") String name,@RequestParam("email") String email,
			@RequestParam("password")String password) throws Exception{
		Employee employee=new Employee();
		employee.setId(empid);
		employee.setName(name);
		employee.setEmail(email);
		employee.setPassword(password);
		int noOfRows=administratorDAO.addEmployee(employee);
		return noOfRows;

	}
	
	@PostMapping("/addTopic")
	public int insertTopic(@RequestParam("topicName") String topicName) throws Exception
	{
		Topics topic=new Topics();
		topic.setName(topicName);
		int noOfRows=administratorDAO.addTopic(topic);
		return noOfRows;	
	}
	
	/*@PostMapping("/deleteTopic")
	public int removeTopic(@RequestParam("topicName") String topicName) throws Exception
	{
		Topics topic=new Topics();
		topic.setName(topicName);
		int noOfRows=administratorDAO.addTopic(topic);
		return noOfRows;
		
	}*/
	
	@GetMapping("/dispayUsers")
	
	public List<Employee> displayUsers()
	{
		
		return employeeDAO.fetchUsers();
	}
	
	
	
}
