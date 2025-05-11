package com.example.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class MailClass {

	@Autowired
	private JavaMailSender massage;
	
	public String SendMail(String otp,String email) {
		try {
		SimpleMailMessage sm=new SimpleMailMessage();
		sm.setFrom("rishu31234@gmail.com");
		sm.setTo(email);
		sm.setSubject("BankEase OTP ");
		sm.setText("Welcome to BankEase!\n\nTo complete your verification process, please use the One-Time Password (OTP) provided below:\n\nOTP: " + otp + 
		           "\n\nThis OTP is valid for the next 10 minutes. For your security, please do not share this OTP with anyone, including BankEase representatives." +
		           "\n\nIf you did not request this OTP, please contact our support team immediately." +
		           "\n\nThank you for choosing BankEase – Banking made easy and secure.");
		massage.send(sm);
		
		return "Otp send Successfully";
		}catch(Exception e) {
			return "Network problem";
		}
	}
}
