package com.cg.healthcaresystem.diagnosticcenter.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "centers")
public class DiagnosticCenter {

	private String centerName;
	@Id
	private String centerId;

	@OneToMany
	private List<Test> listOfTests;

	// private List<String> listOfTests;

	@OneToMany(mappedBy = "center")
	private List<Appointment> appointments;

	public DiagnosticCenter() {

	}

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

	@Override
	public int hashCode() {

		return centerId.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || !(object instanceof DiagnosticCenter))
			return false;
		DiagnosticCenter diagnosticCenter = (DiagnosticCenter) object;
		return this.centerId.equals(diagnosticCenter.getCenterId());
	}

}
