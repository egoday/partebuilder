package com.egoday.partebuilder;

import java.util.Date;

public class Model {

	private final Date date;

	private final String name;

	private final String client;

	private final String place;

	private final String hours;

	private final String fridayHours;

	private final double hoursCount;

	private final double fridayHoursCount;

	public Model(Date date, String name, String client, String place,
			String hours, String fridayHours,
			double hoursCount, double fridayHoursCount) {

		this.date = date;
		this.name = name;
		this.client = client;
		this.place = place;
		this.hours = hours;
		this.fridayHours = fridayHours;
		this.hoursCount = hoursCount;
		this.fridayHoursCount = fridayHoursCount;
	}

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public String getClient() {
		return client;
	}

	public String getPlace() {
		return place;
	}

	public String getHours() {
		return hours;
	}

	public String getFridayHours() {
		return fridayHours;
	}

	public double getHoursCount() {
		return hoursCount;
	}

	public double getFridayHoursCount() {
		return fridayHoursCount;
	}

	public String getMonthName() {
		return String.format("%1$tB %1$tY", date);
	}

}
