package com.resume.entities;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.resume.utils.Utils;

@MappedSuperclass
public abstract class CommonFields extends Base {
	private String name;
	private Date startDate;
	private String startDateString;
	private Date endDate;
	private String endDateString = Utils.TILL_DATE;

	CommonFields() {
		Calendar c = Calendar.getInstance();
		name="";
		startDate = c.getTime();
		endDate = c.getTime();
	}

	public CommonFields(String name, String startDateString, String endDateString) {
		this.name = name;
		this.startDateString = startDateString;
		this.endDateString = endDateString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return Utils.TO_DATE_FN.apply(startDateString);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public Date getEndDate() {
		return Utils.TO_DATE_FN.apply(endDateString);
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}
	
	@Override
	public Object clone() {
		return super.clone();
	}

}