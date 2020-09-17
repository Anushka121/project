package com.cg.healthcaresystem.diagnosticcenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthcaresystem.diagnosticcenter.entities.DiagnosticCenter;

public interface IDiagnosticCenterDao extends JpaRepository<DiagnosticCenter, String> {

}
