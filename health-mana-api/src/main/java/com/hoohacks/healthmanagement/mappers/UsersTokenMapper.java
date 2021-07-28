package com.hoohacks.healthmanagement.mappers;

import com.hoohacks.healthmanagement.models.UsersToken;

public interface UsersTokenMapper {
	UsersToken selectById(int id);

	int insertSelective(UsersToken insertItemUserToken);
	
	int updateByPrimaryKeySelective(UsersToken usersToken);


	UsersToken selectByToken(String token);

	int deleteTokenByPrimaryKey(int id);
}
