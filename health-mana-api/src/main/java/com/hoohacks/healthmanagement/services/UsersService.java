package com.hoohacks.healthmanagement.services;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoohacks.healthmanagement.mappers.UsersMapper;
import com.hoohacks.healthmanagement.mappers.UsersTokenMapper;
import com.hoohacks.healthmanagement.models.Users;
import com.hoohacks.healthmanagement.models.UsersToken;
import com.hoohacks.healthmanagement.utils.Commons;
import com.hoohacks.healthmanagement.utils.Result;

@Service
public class UsersService {
	
	@Resource
	UsersMapper usersMapper;
	
	@Resource
	UsersTokenMapper usersTokenMapper;
	
    public Result login(String ssn, String password){
    	//根据ssn查询user
    	Users user = usersMapper.selectBySsn(ssn);
    	//如果查询结果不为空
		if (user != null) {
			if (!user.getPassWord().equals(password)) {
				//密码错误
				return new Result(101,"Incorrect Password");
			}
			String token = Commons.getRandomString(16);
			
			//通过id查找token
			UsersToken usersToken = usersTokenMapper.selectById(user.getId());
			
			Date now = new Date();
			
			Date expireTime = new Date(now.getTime() + 30 * 24 * 3600 * 1000);//过期时间 30 天
			
			//如果token表里没有查到
			if (usersToken == null) {
				//创建新的UsersToken对象
				UsersToken insertItem = new UsersToken();
				insertItem.setLoginExpireTime(expireTime);
				insertItem.setLastCheckTime(now);
				insertItem.setToken(token);
				insertItem.setId(user.getId());
				//插入数据库
				if(usersTokenMapper.insertSelective(insertItem) > 0) {
					//插入成功
					return new Result(200,"Login Success");
				} else {
					return new Result(300,"Login fail");
				}
			} else {
				//更新UsersToken对象
				usersToken.setLastCheckTime(now);
				usersToken.setLoginExpireTime(expireTime);
				usersToken.setToken(token);
				//插入数据库
				if(usersTokenMapper.updateByPrimaryKeySelective(usersToken) > 0) {
					//插入成功
					return new Result(200, "Login Success");
				} else {
					return new Result(300, "Login fail");
				}
			}
		}
		return new Result(000, "User not found, please register");
    }

	//public boolean deletTokeneByPrimaryKey(Long userId);

	public Result register(String ssn, String passWord, String firstName, String lastName) {
		//ssn是否已被占用
		if(usersMapper.selectBySsn(ssn) != null) {
			//返回100
			return new Result(100,"Your SSN is already registered");
		}
		//新建Users对象
		Users user = new Users();
		user.setSsn(ssn);
		user.setPassWord(passWord);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		//如果插入方法返回大于0
		if(usersMapper.insert(user) > 0) {
			//注册成功
			return new Result(200,"Registered successfully");
		}
		//注册失败
		return new Result(300,"Registration failed");
	}

	//public Boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam, int userId);
}
