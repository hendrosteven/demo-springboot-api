package com.brainmatics.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmatics.dto.LoginDto;
import com.brainmatics.dto.ResponseData;
import com.brainmatics.helpers.MD5Generator;
import com.brainmatics.models.entity.User;
import com.brainmatics.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto data){
		ResponseData response = new ResponseData();
		try {
			User user = userService.login(data.getEmail(), MD5Generator.generate(data.getPassword()));
			if(user == null) {
				response.setStatus(false);
				response.getMessages().add("Login gagal");
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
			}
			
			//berhasil login then create token
			String baseStr = user.getEmail() + ":" + user.getPassword();
			String token = Base64.getEncoder().encodeToString(baseStr.getBytes());
			
			response.setStatus(true);
			response.getMessages().add("Login sukses");
			response.setPayload(token);
			return ResponseEntity.ok(response);
			
			
		}catch(Exception ex) {
			response.setStatus(false);
			response.getMessages().add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody User user){
		ResponseData response = new ResponseData();
		try {
			
			user.setPassword(MD5Generator.generate(user.getPassword()));
			User createdUser = userService.insert(user);
			response.setStatus(true);
			response.getMessages().add("User created");
			response.setPayload(createdUser);
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setStatus(false);
			response.getMessages().add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
