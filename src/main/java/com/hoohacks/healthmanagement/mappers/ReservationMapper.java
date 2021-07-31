package com.hoohacks.healthmanagement.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hoohacks.healthmanagement.models.Reservation;

@Mapper
public interface ReservationMapper {
	
	int insertReservation(Reservation reserve);
	
	//int deleteReservationBySsn(String ssn);
	
	//Reservation getReservationBySsn(String ssn);
	
}
