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
import com.example.demo.mail.MailClass;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignupController {

    private final ControllerMain controllerMain;
	
	@Autowired
	AccountRepo acc;
	@Autowired
	Cust_Repo cust;
	@Autowired
	MailClass mail;

    SignupController(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }
	
	@GetMapping("/signup")
	public String signup() {
	
		return "signup";
	}
	
    @PostMapping("/signup")
	public String afterSignup(@RequestParam String fullName, 
			@RequestParam String phone,
			@RequestParam String email,
			@RequestParam String address,
			@RequestParam String dob,
			@RequestParam String accountType,
			@RequestParam String password
			,Model m,
			HttpSession session)
    {
    	
       
       long mobile=0;
       try {
        mobile=Long.parseLong(phone);
       }
       catch(Exception e) {
    	   m.addAttribute("msg", "Error mobile number");
    	   return "signup";
       }
    	
       Customer cus=new Customer();
       cus.setName(fullName);
       cus.setAddress(address);
       cus.setEmail(email);
       cus.setPhone(mobile);
       cus.setDate(dob);
       
       
       
       Account ac=new Account();
       
       ac.setPassword(password);
       ac.setBalance(0);
       
       Random random = new Random();
       long accountNum = 1000000000L+(long)(random.nextDouble() * 9000000000L);
       
       
       
       ac.setAccount_number(accountNum);
       ac.setAc_type(accountType);
       
       
//       cust.save(cus);
//       acc.save(ac);
       
       session.setAttribute("customer", cus);
       session.setAttribute("account", ac);
       
       Random rand = new Random();
       int ot = 10000 + rand.nextInt(90000);  
       String otp=Integer.toString(ot);
       session.setAttribute("otp", otp);
       
      String msgg= mail.SendMail(otp, email);
      String res=msgg+" : "+email;
       
      
      m.addAttribute("ms",res);
		return "otp";
	}
    
    @PostMapping("/val")
    public String varifyOTP(@RequestParam String otp,Model m,HttpSession session) {
    	Customer customer=(Customer) session.getAttribute("customer");
    	Account account=(Account) session.getAttribute("account");
    	String ottp=(String) session.getAttribute("otp");
    	
    	
    	
    	if(otp.equals(ottp)) {
    		System.out.println("Match Ho gya");
    		cust.save(customer);
        	int id=customer.getId();
        	account.setCust_id(id);
        	acc.save(account);
        	return "login";
    	}
    	else {
    		System.out.println("Match nhi hua");
    		m.addAttribute("ms", "Otp is not mathcing");
    	}
    	

    	return "otp";
    }

    
    
}
