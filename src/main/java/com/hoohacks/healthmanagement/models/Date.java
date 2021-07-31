package com.hoohacks.healthmanagement.models;

public class Date {
	private int dateId;
	
	private String date;
	
	private int locationId;
	
	private int morningSpot;
	
	private int afternoonSpot;
	
	private String locationName;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getAfternoonSpot() {
		return afternoonSpot;
	}

	public void setAfternoonSpot(int afternoonSpot) {
		this.afternoonSpot = afternoonSpot;
	}

	public int getMorningSpot() {
		return morningSpot;
	}

	public void setMorningSpot(int morningSpot) {
		this.morningSpot = morningSpot;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDateId() {
		return dateId;
	}

	public void setDateId(int dateId) {
		this.dateId = dateId;
	}
}
