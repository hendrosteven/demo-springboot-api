package com.brainmatics.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmatics.dto.KaryawanDto;
import com.brainmatics.dto.ResponseData;
import com.brainmatics.models.entity.Karyawan;
import com.brainmatics.models.repo.DepartemenRepo;
import com.brainmatics.services.KaryawanService;

@RestController
@RequestMapping("/api/v1/karyawan")
public class KaryawanController {

	@Autowired
	private KaryawanService karyawanService;
	
	@Autowired
	private DepartemenRepo depRepo;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		ResponseData response = new ResponseData();
		try {
			response.setPayload(karyawanService.findAll());
			response.setStatus(true);
			response.getMessages().add("Load all karyawan");
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setStatus(false);
			response.getMessages().add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody KaryawanDto karyawan, Errors errors){
		ResponseData response = new ResponseData();
		if(!errors.hasErrors()) {
			Karyawan kry = new Karyawan();
			kry.setNip(karyawan.getNip());
			kry.setFirstName(karyawan.getFirstName());
			kry.setLastName(karyawan.getLastName());
			kry.setAddress(karyawan.getAddress());
			kry.setEmailAddress(karyawan.getEmailAddress());
			kry.setPhoneNumber(karyawan.getPhoneNumber());
			kry.setDepartemen(depRepo.findById(karyawan.getDepartemenId()).get());
			try {
				Karyawan result = karyawanService.insert(kry);
				response.setStatus(true);
				response.getMessages().add("Karyawan saved");
				response.setPayload(result);
				return ResponseEntity.ok(response);
			}catch(Exception ex) {
				response.setStatus(false);
				response.getMessages().add(ex.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}	
		}else {
			for(ObjectError err :errors.getAllErrors()) {
				response.getMessages().add(err.getDefaultMessage());
			}
			response.setStatus(false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
}
