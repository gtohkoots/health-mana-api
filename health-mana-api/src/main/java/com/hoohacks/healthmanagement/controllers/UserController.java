package com.hoohacks.healthmanagement.controllers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoohacks.healthmanagement.params.Login;
import com.hoohacks.healthmanagement.params.Register;
import com.hoohacks.healthmanagement.services.UsersService;
import com.hoohacks.healthmanagement.utils.Judges;
import com.hoohacks.healthmanagement.utils.Result;

@RestController
public class UserController {
	
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	//注册接口
	public Result registration(@RequestBody Register registerParam) {
		//判断是否合法ssn
		Result result;
		if (!Judges.ssnValidity(registerParam.getSsn())) {
			//invalid
			result = new Result(300,"Invalid ssn, please try again");
			return result;
		}
		result = usersService.register(registerParam.getSsn(),registerParam.getPassWord(),registerParam.getFirstName(),registerParam.getLastName());
		return result;
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public Result login(@RequestBody Login loginParam) {
		Result result;
		if (!Judges.ssnValidity(loginParam.getSsn())) {
			//invalid
			result = new Result(300,"Invalid ssn, please try again");
			return result;
		}
		result = usersService.login(loginParam.getSsn(), loginParam.getPassword());
		return result;
	}
}



