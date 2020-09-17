package com.cg.healthcaresystem.diagnosticcenter.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcaresystem.diagnosticcenter.dto.CenterDetailsDto;
import com.cg.healthcaresystem.diagnosticcenter.dto.CenterDto;
import com.cg.healthcaresystem.diagnosticcenter.entities.DiagnosticCenter;
import com.cg.healthcaresystem.diagnosticcenter.exceptions.CenterNotFoundException;
import com.cg.healthcaresystem.diagnosticcenter.service.IDiagnosticCenterService;

@RestController
@RequestMapping("/centers")
@Validated
public class DiagnosticCenterRestController {
	private static final Logger Log = LoggerFactory.getLogger(DiagnosticCenterRestController.class);

	@Autowired
	private IDiagnosticCenterService service;

	@GetMapping
	ResponseEntity<List<CenterDetailsDto>> viewAll() {
		List<DiagnosticCenter> centerList = service.viewAllDiagnosticCenter();
		List<CenterDetailsDto> list = convertToDetailsDto(centerList);
		ResponseEntity<List<CenterDetailsDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;
	}

	@PostMapping("/add")
	ResponseEntity<CenterDetailsDto> add(@Valid @RequestBody CenterDto reqDto) {
		DiagnosticCenter center = new DiagnosticCenter();
		center.setCenterId(reqDto.getCenterId());
		center.setCenterName(reqDto.getCenterName());
		center.setListOfTests(reqDto.getListOfTests());
		center.setAppointments(reqDto.getAppointments());
		center = service.addDiagnosticCenter(center);
		CenterDetailsDto dto = convertToDetailsDto(center);
		ResponseEntity<CenterDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}

	@DeleteMapping("/delete/{centerId}")
	ResponseEntity<Boolean> delete(@PathVariable("centerId") String centerId) {
		boolean result = service.removeDiagnosticCenter(centerId);
		ResponseEntity<Boolean> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;

	}

	@GetMapping("/get/{centerId}")
	ResponseEntity<CenterDetailsDto> getById(@PathVariable("centerId") String centerId) {
		DiagnosticCenter center = service.findDiagnosticCenterById(centerId);
		CenterDetailsDto dto = convertToDetailsDto(center);
		ResponseEntity<CenterDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}

	@PutMapping("/modify/{centerId}")
	ResponseEntity<CenterDetailsDto> modify(@PathVariable("centerId") String centerId,
			@Valid @RequestBody CenterDto dto) {

		DiagnosticCenter center = service.findDiagnosticCenterById(centerId);
		center.setCenterId(dto.getCenterId());
		center.setCenterName(dto.getCenterName());
		center.setListOfTests(dto.getListOfTests());
		center.setAppointments(dto.getAppointments());
		center = service.modifyCenter(center);
		CenterDetailsDto details = convertToDetailsDto(center);
		ResponseEntity<CenterDetailsDto> response = new ResponseEntity<>(details, HttpStatus.OK);
		return response;

	}

	public List<CenterDetailsDto> convertToDetailsDto(Collection<DiagnosticCenter> centerList) {
		List<CenterDetailsDto> dtos = new ArrayList<>();
		for (DiagnosticCenter center : centerList) {
			CenterDetailsDto dto = convertToDetailsDto(center);
			dtos.add(dto);
		}
		return dtos;
	}

	public CenterDetailsDto convertToDetailsDto(DiagnosticCenter center) {
		CenterDetailsDto dto = new CenterDetailsDto();
		dto.setCenterId(center.getCenterId());
		dto.setCenterName(center.getCenterName());
		dto.setListOfTests(center.getListOfTests());
		dto.setAppointments(center.getAppointments());

		return dto;
	}

	@ExceptionHandler(CenterNotFoundException.class)
	public ResponseEntity<String> handleCenterNotFound(CenterNotFoundException ex) {
		Log.error("center not found exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
		Log.error("constraint violation", ex);
		String msg = "failed to match contraint";
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable ex) {
		Log.error("Something went wrong", ex);
		String msg = "something went wrong";
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
}
