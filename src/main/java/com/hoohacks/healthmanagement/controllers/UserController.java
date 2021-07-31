package com.hoohacks.healthmanagement.controllers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.hoohacks.healthmanagement.mappers.UsersMapper;
import com.hoohacks.healthmanagement.mappers.UsersTokenMapper;
import com.hoohacks.healthmanagement.models.Users;
import com.hoohacks.healthmanagement.models.UsersToken;
import com.hoohacks.healthmanagement.params.Login;
import com.hoohacks.healthmanagement.params.Register;
import com.hoohacks.healthmanagement.services.UsersService;
import com.hoohacks.healthmanagement.utils.Commons;
import com.hoohacks.healthmanagement.utils.Judges;
import com.hoohacks.healthmanagement.utils.Result;

@CrossOrigin(origins="http://localhost:8080", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@Resource
	private UsersMapper usersMapper;
	
	@Resource 
	private UsersTokenMapper usersTokenMapper;

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
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public Result logout(@RequestBody UsersToken tokenParam) {
		Users user = usersService.getUsersByToken(tokenParam.getToken());
		if(user != null) {
			UsersToken userToken = usersTokenMapper.selectByToken(tokenParam.getToken());
			if(usersTokenMapper.deleteTokenByPrimaryKey(userToken.getId()) > 0) {
				return new Result(200,"Logout Success");
			}
		}
		return new Result(300,"Not Logged In Yet");
		
	}
	
	@RequestMapping(value = "/user/checkOtherUser", method = RequestMethod.POST)
	public Result checkOtherUser(@RequestBody Login checkParam) {
		Result result;
		if (!Judges.ssnValidity(checkParam.getSsn())) {
			//invalid
			result = new Result(404,"Invalid ssn, please try again");
			return result;
		}
		Users user = usersMapper.selectBySsn(checkParam.getSsn());
		if(user != null) {
			result = new Result(200,"Info Found");
			result.setData(user);
			return result;
		}
		return new Result(300,"This SSN is not registered yet");
	}
	
	@RequestMapping(value = "/user/selfCheck",method = RequestMethod.POST)
	public Result selfCheck(@RequestBody UsersToken tokenParam) {
		Result result;
		Users user = usersService.getUsersByToken(tokenParam.getToken());
		if(user != null) {
			result = new Result(200, "Info Found");
			result.setData(user);
			return result;
		}
		return new Result(300,"Invalid login Status");
	}
	
	@RequestMapping(value= "user/currentUserName", method = RequestMethod.GET)
	public Result getCurrName(NativeWebRequest webRequest) {
		String token = webRequest.getHeader("token");
		if (token != null) {
			Users user = usersService.getUsersByToken(token);
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String fullName = firstName + " " + lastName;
			Result res = new Result(200,"User Found");
			res.setData(fullName);
			System.out.println("result" + res.toString());
			return res;
		}
		else {
			return new Result(404,"Token not found");
		}
	}
	
	@RequestMapping(value= "user/currentUserssn", method = RequestMethod.GET)
	public Result getCurrSSN(NativeWebRequest webRequest) {
		String token = webRequest.getHeader("token");
		if (token != null) {
			Users user = usersService.getUsersByToken(token);
			Result res = new Result(200,"User Id Found");
			res.setData(user.getSsn());
			System.out.println("result" + res.toString());
			return res;
		}
		else {
			return new Result(404,"Token not found");
		}
	}
}



