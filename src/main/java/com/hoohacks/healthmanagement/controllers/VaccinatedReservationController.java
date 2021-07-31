package com.hoohacks.healthmanagement.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoohacks.healthmanagement.mappers.DateMapper;
import com.hoohacks.healthmanagement.mappers.LocationMapper;
import com.hoohacks.healthmanagement.mappers.ReservationMapper;
import com.hoohacks.healthmanagement.mappers.UsersMapper;
import com.hoohacks.healthmanagement.mappers.UsersTokenMapper;
import com.hoohacks.healthmanagement.models.Date;
import com.hoohacks.healthmanagement.models.Reservation;
import com.hoohacks.healthmanagement.services.UsersService;
import com.hoohacks.healthmanagement.utils.Result;

@RestController
public class VaccinatedReservationController {
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private UsersMapper usersMapper;
	
	@Resource 
	private UsersTokenMapper usersTokenMapper;
	
	@Resource
	private ReservationMapper reservationMapper;
	
	@Resource
	private DateMapper dateMapper;
	
	@Resource
	private LocationMapper locationMapper;
	
	@RequestMapping(value = "/user/reserve", method = RequestMethod.POST)
	public Result reserve(@RequestBody Reservation reserveParam) {
		System.out.println("Param Received: " + reserveParam.getLocationName());
		System.out.println("Param Received: " + reserveParam.getDate());
		System.out.println("Param Received: " + reserveParam.getUserSsn());
		Date date = dateMapper.checkAvailability(reserveParam);
		if(reserveParam.getTime() == 0) {
			//上午
			if(date.getMorningSpot() == 0) {
				//订满
				return new Result(100,"Morning Spots are all reserved.");
			}
			//没订满
			if(reservationMapper.insertReservation(reserveParam) > 0) {
				//morning spot --
				dateMapper.morningReservationSuccess(reserveParam);
				return new Result(200, "Reserve successfully");
			}
		} else if (reserveParam.getTime() == 1) {
			//下午
			if(date.getAfternoonSpot() == 0) {
				//订满
				return new Result(101, "Afternoon Spots are all reserved");
			}
			//没订满
			if(reservationMapper.insertReservation(reserveParam) > 0) {
				//afternoon spot --
				dateMapper.afternoonReservationSuccess(reserveParam);
				return new Result(200, "Reserve successfully");
			}
		}
		return new Result(000, "Unknown Error.");
	}
	
	@RequestMapping(value = "/display/availability", method = RequestMethod.GET)
	public Result displayAvailability(String locationName){
		List<Date> dateList = dateMapper.displayAvailability(locationName);
		Result result = new Result(200, "Availability got.");
		result.setData(dateList);
		return result;
	}
	
	@RequestMapping(value = "/display/locations", method = RequestMethod.GET)
	public Result displayLocations() {
		List<String> locationList = locationMapper.getLocation();
		Result result = new Result(200, "Location got");
		result.setData(locationList);
		return result;
	}
	
	
}
