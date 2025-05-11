package com.example.demo.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Db.Account;
import com.example.demo.Db.Customer;
import com.example.demo.Db.Loan;
import com.example.demo.Db.LoanRepo;
import com.example.demo.Db.Tran;

import jakarta.servlet.http.HttpSession;



@Controller
public class LoanModule {
	@Autowired
	LoanRepo loana;
	
	@GetMapping("/loan")	
     public String loan(HttpSession session,Model m) {
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
		
		return "loan";
	}
	
	@PostMapping("/loanapply")
	public String afterLoanApply(HttpSession session,Model m,
			@RequestParam String calculatedEmi,
			@RequestParam String calculatedInterest,
			@RequestParam String calculatedTotal,
			@RequestParam String loanType,
			@RequestParam String loanAmount,
			@RequestParam String loanTerm,
			@RequestParam String interestRate,
			@RequestParam String fullName,
			@RequestParam String email,
			@RequestParam String phone,
			@RequestParam String employment,
			@RequestParam String income,
			@RequestParam String purpose,
			@RequestParam String existingLoans
			
			) {
		
		List<Tran> t =(List<Tran>)session.getAttribute("statements");
		Account ac=(Account)session.getAttribute("account");
		Customer cu=(Customer) session.getAttribute("customer");
		Loan ll=(Loan) session.getAttribute("activeLoan");
		m.addAttribute("activeLoan", ll);
		m.addAttribute("statements", t);
		m.addAttribute("account", ac);
		m.addAttribute("customer", cu);
		
//		
//		System.out.println(calculatedEmi);
//		System.out.println(calculatedInterest);
//		System.out.println(calculatedTotal);
//		System.out.println(loanAmount);
		Loan loan=new Loan();
		
		double cal_emi2=0;
		double cal_interest=0;
		double cal_total=0;
		double interest_rate=0;
		double loan_am=0;
		int term=0;
		
		try {
			cal_emi2=Double.parseDouble(calculatedEmi);
			cal_interest=Double.parseDouble(calculatedInterest);
			cal_total=Double.parseDouble(calculatedTotal);
			interest_rate=Double.parseDouble(interestRate);
			loan_am=Double.parseDouble(loanAmount);
			term=Integer.parseInt(loanTerm);
		}
		catch(Exception e) {
			
			m.addAttribute("activeLoan", ll);
			m.addAttribute("statements", t);
			m.addAttribute("account", ac);
			m.addAttribute("customer", cu);
			m.addAttribute("msg", "Error input");
			
			return "loan";
		}
		
		loan.setCal_emi(cal_emi2);
		loan.setCal_interest(cal_interest);
		loan.setCal_total(cal_total);
		loan.setInterest_rate(interest_rate);
		loan.setLoan_amount(loan_am);
		loan.setLoan_year(term);
		loan.setEmp_status(employment);
		loan.setExis_loan(existingLoans);
		loan.setIncome(income);
		loan.setLoan_type(loanType);
		loan.setMail(email);
		loan.setMobile_no(phone);
		loan.setName(fullName);
		loan.setPurpose(purpose);
		int id=cu.getId();
		loan.setCust_id(id);
		
		loana.save(loan);
		m.addAttribute("msg", "Loan Apply successfull");
		
		session.setAttribute("activeLoan", loan);
		return "loan";
	}
	

}
