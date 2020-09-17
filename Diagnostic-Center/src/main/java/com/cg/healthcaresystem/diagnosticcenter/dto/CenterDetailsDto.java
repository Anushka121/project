package com.cg.healthcaresystem.diagnosticcenter.dto;

import java.util.List;

import com.cg.healthcaresystem.diagnosticcenter.entities.Appointment;
import com.cg.healthcaresystem.diagnosticcenter.entities.Test;

public class CenterDetailsDto {

	private String centerId;
	private String centerName;

	private List<Test> listOfTests;
	private List<Appointment> appointments;

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public List<Test> getListOfTests() {
		return listOfTests;
	}

	public void setListOfTests(List<Test> listOfTests) {
		this.listOfTests = listOfTests;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
