package com.hoohacks.healthmanagement.utils;

import java.util.Random;

import javax.annotation.Resource;

import com.hoohacks.healthmanagement.mappers.UsersMapper;
import com.hoohacks.healthmanagement.mappers.UsersTokenMapper;
import com.hoohacks.healthmanagement.models.Users;
import com.hoohacks.healthmanagement.models.UsersToken;
import com.hoohacks.healthmanagement.services.UsersService;

public class Commons {
	@Resource
	UsersService usersService;
	
	@Resource
	UsersTokenMapper usersTokenMapper;
	
	@Resource
	UsersMapper usersMapper;
	
	// GetRandomHexNumString 生成随机字符串
	public static String getRandomString(int length){
	    String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    Random random=new Random();
	    StringBuffer sb=new StringBuffer();
	    for(int i=0;i<length;i++){
	      int number=random.nextInt(62);
	      sb.append(str.charAt(number));
	    }
	    return sb.toString();
	}
	
}
