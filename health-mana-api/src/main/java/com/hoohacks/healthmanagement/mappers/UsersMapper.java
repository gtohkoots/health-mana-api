package com.hoohacks.healthmanagement.mappers;

import org.apache.ibatis.annotations.Mapper;
import com.hoohacks.healthmanagement.models.Users;

@Mapper
public interface UsersMapper {
	
	Users selectBySsn(String ssn);
	
	//other
	Users selectByName(String loginName);
	
	int insert(Users user);

	int updateUserInfo(Users user);
}
