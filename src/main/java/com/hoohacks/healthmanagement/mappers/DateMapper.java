package com.hoohacks.healthmanagement.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hoohacks.healthmanagement.models.Date;
import com.hoohacks.healthmanagement.models.Reservation;

@Mapper
public interface DateMapper {
	
	Date checkAvailability(Reservation reserve);
	
	List<Date> displayAvailability(String locationName);
	
	void morningReservationSuccess(Reservation reserve);
	
	void afternoonReservationSuccess(Reservation reserve);
}
