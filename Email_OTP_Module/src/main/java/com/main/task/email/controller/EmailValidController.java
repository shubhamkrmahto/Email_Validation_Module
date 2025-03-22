package com.main.task.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.main.task.email.service.EmailValidservice;

@RestController
public class EmailValidController {


	@Autowired private EmailValidservice es;
	
	@PostMapping("/sendOTP/{email}")
	public ResponseEntity<String> sendOTP(@PathVariable("email") String email)
	{
		
		es.sendOTP(email);
		System.out.println("OTP has been sent successfully to your email.");
		return new ResponseEntity<String>("OTP has been sent successfully to your email.", HttpStatus.CREATED);
	}
	
	@PostMapping("verifyOTP/{otp}")
	public ResponseEntity<String> verifyOTP(@PathVariable("otp") String otp)
	{
		
		if(es.validateOTP(otp)) {
			System.out.println("OTP is valid. Your email id is registered successfully.");
		return new ResponseEntity<String>("OTP is valid. Your email id is registered successfully.", HttpStatus.ACCEPTED);
		}
		else {
			System.out.println("OTP is invalid.");
			return new ResponseEntity<String>("OTP is invalid.", HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
}

