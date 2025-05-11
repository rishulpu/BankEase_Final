package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Db.Account;
import com.example.demo.Db.AccountRepo;
import com.example.demo.Db.Cust_Repo;
import com.example.demo.Db.Customer;
import com.example.demo.Service.CustomerMethod;
import com.example.demo.mail.MailClass;

import jakarta.servlet.http.HttpSession;

@Controller
public class forgotPass {

	@Autowired
	Cust_Repo cust;
	
	@Autowired
	AccountRepo acc;

	@Autowired
	MailClass mail;

	@Autowired
	CustomerMethod cm;
	
	
	@GetMapping("/forgot")
	public String forgetPass() {
		return "forgot";
	}

	@PostMapping("/resetOtp")
	public String resetOTP(@RequestParam String email,HttpSession session,Model m) {
//		System.out.println(email);

		Iterable<Customer> acco = cust.findAll();
		Customer res = null;
		for (Customer cu : acco) {
			if (cu.getEmail().equals(email)) {
				Random rand = new Random();
				int ot = 10000 + rand.nextInt(90000);
				String otp = Integer.toString(ot);
                mail.SendMail(otp, email);
				res = cu;
				session.setAttribute("otp", otp);
				session.setAttribute("customer", cu);
				return "verifyOTP";
			}
			
				
			
		}
		m.addAttribute("msg","Mail not found");
		return "forgot";

		
	}
	
	@PostMapping("/verify-otp")
	public String verifyOTP(@RequestParam String otp,@RequestParam String newPassword,HttpSession session
			,Model m) {
		String Otp=(String) session.getAttribute("otp");
		Customer cust=(Customer) session.getAttribute("customer");
		int id=cust.getId();
		
		Account a=cm.searchAccoutById(id);
		
		if(otp.equals(Otp)) {
			a.setPassword(newPassword);
			acc.save(a);
		}
		else {
			m.addAttribute("msg", "WRONG OTP! ");
			return "verifyOTP";
		}
		
		
		m.addAttribute("successMsg", "Password successfully reset.");
		return "verifyOTP";
	}
}
