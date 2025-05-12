package com.example.demo.controller;
import com.example.demo.ZzBankEaseFinalApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Db.Account;
import com.example.demo.Db.AccountRepo;
import com.example.demo.Db.Cust_Repo;
import com.example.demo.Db.Customer;
import com.example.demo.Db.Loan;
import com.example.demo.Db.LoanRepo;
import com.example.demo.Db.Tran;
import com.example.demo.Db.Trans_Repo;
import com.example.demo.Service.CustomerMethod;
import com.example.demo.mixClass.TransAndAccount;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControllerMain {

	
	@Autowired
	AccountRepo acc;
	
	@Autowired
	Cust_Repo cust;
	
	@Autowired
	Trans_Repo trans;
	
	@Autowired
	CustomerMethod cmethod;

	@Autowired
    LoanRepo loan;
	
	
	@GetMapping("/login")
	public String name() {
		return "login";
	}
	
	@GetMapping("/log")
	public String afterLogin(@RequestParam String email,@RequestParam String password,Model m,
			HttpSession session) {
		
		
		int id=cmethod.returnIdfromEmailandPass(email, password);
		System.out.println("Login is runnnig");
		if(id==0) {
			m.addAttribute("msg","Wrong Email or Password");
		    return "login";
		}
		
		Account account=cmethod.searchAccoutById(id);
		Customer customer=cmethod.searchCustomerById(id);
		List<Tran> tran=cmethod.searchTranById(id);
		Loan loan=cmethod.searchById(id);
		
		
		
		m.addAttribute("account", account);
		m.addAttribute("statements", tran);
		m.addAttribute("customer", customer);
		m.addAttribute("activeLoan", loan);
		
		session.setAttribute("account", account);
		session.setAttribute("statements", tran);
		session.setAttribute("customer", customer);
		session.setAttribute("activeLoan", loan);
		
		
		return "home";
	}
	
	
	@GetMapping("/statement")
	public String statement(Model m,HttpSession session) {
		
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		
		
		return "statement";
	}
	
	
	
	@GetMapping("/profile")
	public String profile(HttpSession session,Model m) {
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		return "profile";
	}
	@GetMapping("/deposit")
	public String deposite(HttpSession session,Model m) {
		
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		return "deposite";
		
	}
	@PostMapping("afterDeposite")
	public String afterDeposit(@RequestParam String description,@RequestParam String amount,
			HttpSession session,Model m){

		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		int amoun=0;
		
		try {
		amoun=Integer.parseInt(amount);
		}
		catch(Exception e) {
			m.addAttribute("msg", "Please put digit in deposite like '1000'");
			m.addAttribute("statements", t);
			m.addAttribute("account", ac);
			m.addAttribute("customer", cu);
			m.addAttribute("activeLoan", ll);
			
			return "deposite";
			
		}
		TransAndAccount actj=cmethod.TransactionWithdeposite(amoun, description,ac.getCust_id());
		Account act=actj.getAc();
		List<Tran> tran=actj.getTran();
		m.addAttribute("account", act);
		m.addAttribute("statements", tran);
		m.addAttribute("msg", "Balance Updated suceessfully");
		session.setAttribute("account", act);
		session.setAttribute("statements", tran);
		
		
		return "deposite";
		
	}
	
	@GetMapping("/withdraw")
	public String withdraw(HttpSession session,Model m) {
		
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		return "withdraw";
		
	}
	
	@PostMapping("afterWithdraw")
	public String afterWithdraw(@RequestParam String description,@RequestParam String amount,
			HttpSession session,Model m){

		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		int amoun=0;
		
		try {
		amoun=Integer.parseInt(amount);
		}
		catch(Exception e) {
			m.addAttribute("msg", "Please put digit like '1000'");
			m.addAttribute("statements", t);
			m.addAttribute("account", ac);
			m.addAttribute("customer", cu);
			m.addAttribute("activeLoan", ll);
			return "withdraw";
			
		}
		TransAndAccount actj=cmethod.TransactionWithWithdraw(amoun, description,ac.getCust_id());
		
		if(actj==null) {
			m.addAttribute("msg", "Itna paise nhi hai aapke account me");
			return "withdraw";
		}
		
		Account act=actj.getAc();
		List<Tran> tran=actj.getTran();
		m.addAttribute("account", act);
		m.addAttribute("statements", tran);
		session.setAttribute("account", act);
		session.setAttribute("statements", tran);
		
		
		m.addAttribute("msg", "Balance Updated suceessfully");
		
		return "withdraw";
		
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session,Model m) {
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		
		return "home";
	}
	
	@GetMapping("/changePass")
	public String changePass(Model m,HttpSession session) {
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
	
		return "ChangePassword";
		
	}
	@PostMapping("/updatePassword")
	public String updatePassword(Model m,HttpSession session,@RequestParam String currentPassword,
			@RequestParam String newPassword,
			@RequestParam String confirmPassword) {
		
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		boolean tt=cmethod.changePass(ac.getCust_id(), currentPassword, newPassword);
		
		if(tt) {
			m.addAttribute("msg", "Password Change Successfully");
		}
		else {
			m.addAttribute("msg", "Password in not matching ");
		}
		
		
		return "ChangePassword";
	}
	
	@GetMapping("/transfer")
	public String transfer(Model m,HttpSession session) {
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
	
		return "transfer";
	}
	
	@PostMapping("/afterFund")
	public String afterTransfer(HttpSession session,Model m,@RequestParam String receiverAccount,
			@RequestParam String receiverName,@RequestParam String amount,
			@RequestParam String description) {
		
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		
		int Amo=0;
		try {
          Amo=Integer.parseInt(amount);
		}
		catch(Exception e) {
			
		}
		Long Acco=Long.parseLong(receiverAccount);
		
		boolean res=cmethod.fundTransfer(Amo, receiverName,Acco);
		if(res==true) {
			TransAndAccount actj=cmethod.TransactionWithWithdraw(Amo, description,ac.getCust_id());
			Account act=actj.getAc();
			List<Tran> tran=actj.getTran();
			m.addAttribute("account", act);
			m.addAttribute("statements", tran);
			
			session.setAttribute("account", act);
			session.setAttribute("statements", tran);
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			
				
			}
			m.addAttribute("msg", "Sucessfully ");
			
            
		}else {
			m.addAttribute("msg", "Accont number not found");
		}
		
		return "transfer";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	
}
