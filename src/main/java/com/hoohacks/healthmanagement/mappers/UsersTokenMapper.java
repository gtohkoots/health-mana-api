package com.hoohacks.healthmanagement.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hoohacks.healthmanagement.models.UsersToken;

@Mapper
public interface UsersTokenMapper {
	UsersToken selectById(int id);

	int insertSelective(UsersToken insertItemUserToken);
	
	int updateByPrimaryKeySelective(UsersToken usersToken);

	UsersToken selectByToken(String token);

	int deleteTokenByPrimaryKey(int id);
}
