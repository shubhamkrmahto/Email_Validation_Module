package com.main.task.email.serviceimp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.main.task.email.service.EmailValidservice;

@Service
public class EmailValidServiceImp implements EmailValidservice {
	
	@Autowired private JavaMailSender jms;
	
	@Value("${spring.mail.username}") private String fromName;	
	
	private final Random random = new Random();
	
	private String generatedOTP;
	
	public String generateOTP()
	{
		generatedOTP = String.format("%06d", random.nextInt(999999));
		
		return generatedOTP;
	}
	
	public boolean validateOTP(String getOTP)
	{
		
		return getOTP.equals(generatedOTP);
	}
	
	public void sendOTP(String email) {
		
		String otp = generateOTP();
		
		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setFrom(fromName);
		smm.setTo(email);
		smm.setSubject("Email registration OTP");
		smm.setText("Your OTP is : "+otp);
		
		jms.send(smm);
		
	}
	
}
