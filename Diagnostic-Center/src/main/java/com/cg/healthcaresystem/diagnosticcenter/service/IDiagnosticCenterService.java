package com.cg.healthcaresystem.diagnosticcenter.service;

import java.util.List;

import com.cg.healthcaresystem.diagnosticcenter.entities.DiagnosticCenter;

public interface IDiagnosticCenterService {

	public DiagnosticCenter addDiagnosticCenter(DiagnosticCenter center);

	public List<DiagnosticCenter> viewAllDiagnosticCenter();

	public DiagnosticCenter findDiagnosticCenterById(String center);

	public boolean removeDiagnosticCenter(String centerId);

	public DiagnosticCenter modifyCenter(DiagnosticCenter center);

}