package com.main.task.email.service;

public interface EmailValidservice {

	public void sendOTP(String email);
	
	public boolean validateOTP(String getOTP);
	
	
}
