package com.example.demo.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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

@Controller
public class AdminPanel {
	@Autowired
	private adminMethod admin;
	@Autowired
	AccountRepo acc;
	@Autowired
	Cust_Repo customer;
	@Autowired
	CustomerMethod cus;
	@Autowired
	Trans_Repo tran;
	@Autowired
	LoanRepo lo;

	@GetMapping("/admin")
	public String admin() {
		return "admin-login";

	}

	@PostMapping("/authenticate")
	public String authenticate(@RequestParam String username, @RequestParam String password, Model m) {

		String user = "admin";
		String pass = "admin";

		if (username.equals(user) && pass.equals(password)) {
			return "dashboard";
		}
		m.addAttribute("msg", "Wrong admin user& password");
		return "admin-login";
	}
	
	@GetMapping("/dash")
	public String dashboard() {
		
		return "dashboard";
	}

	@GetMapping("/useracc")
	public String userAcc(Model m) {
		List<CutomerandAccount> all = admin.allUsers();
		if (all != null) {

			m.addAttribute("customers", all);
			return "users-accounts";
		}
		m.addAttribute("msg", "Not found");

		return "users-accounts";
	}

	@GetMapping("/trans")
	public String trans() {
		return "transactions";
	}

	@PostMapping("/searchbyid")
	public String searchbyid(@RequestParam String customerId, Model m) {

		int id = 0;
		try {
			id = Integer.parseInt(customerId);
		} catch (Exception e) {
			m.addAttribute("msg", "Enter number in customer id for searching");
           return "dashboard";
		}

		CutomerandAccount all = admin.searchCustandAccByID(id);

		if (all != null) {

			m.addAttribute("customers", all);
			return "dashboard";
		}
		m.addAttribute("msg", "Not found");

		return "dashboard";
	}

	@PostMapping("/searchemail")
	public String searchByEmail(@RequestParam String customerEmail, Model m) {

		CutomerandAccount all = admin.searchByemail(customerEmail);

		if (all != null) {
			m.addAttribute("customers", all);
			return "dashboard";
		}
		m.addAttribute("msg", "Not found");

		return "dashboard";
	}

	@PostMapping("/searchname")
	public String searchByName(@RequestParam String customerName, Model m) {
		List<CutomerandAccount> acc = admin.searchByname(customerName);
		if (!acc.isEmpty()) {
			m.addAttribute("customers", acc);
			return "dashboard";
		}

		m.addAttribute("msg", "Not found");
		return "dashboard";

	}

	@PostMapping("/delete")
	public String delete(@RequestParam String cust_id, Model m) {

		int id = Integer.parseInt(cust_id);

		acc.deleteById(id);
		Customer cc = cus.searchCustomerById(id);
		customer.delete(cc);

		Iterable<Tran> tt = tran.findAll();
		for (Tran t : tt) {
			if (t.getCust_id() == id) {
				tran.delete(t);
			}
		}

		Iterable<Loan> lk = lo.findAll();

		for (Loan k : lk) {
			if (k.getCust_id() == id) {
				lo.delete(k);
			}
		}

		System.out.println("Deleted ");
		m.addAttribute("msg", "ONE USER DELETED SUCCESS");

		List<CutomerandAccount> all = admin.allUsers();
		if (all != null) {

			m.addAttribute("customers", all);
			return "users-accounts";
		} else {
			m.addAttribute("msg", "Not found");
		}

		return "users-accounts";

	}

	@PostMapping("/adduser")
	public String adduser(@RequestParam String fullName, @RequestParam String email, @RequestParam String phone,
			@RequestParam String address, @RequestParam String accountType, @RequestParam String initialDeposit,
			Model m) {

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = today.format(formatter);
		
		Customer cc=new Customer();
		cc.setName(fullName);
		cc.setEmail(email);
		Long l=Long.parseLong(phone);
		cc.setPhone(l);
		cc.setAddress(address);
		cc.setDate(date);
		
		customer.save(cc);
		
		
		int id=cc.getId();
		
		Account ac=new Account();
		ac.setCust_id(id);
		ac.setAc_type(accountType);
		Long bal=Long.parseLong(initialDeposit);
		ac.setBalance(bal);
		ac.setPassword("123456");
		
		 Random rand = new Random();
	        long min = 1000000000L;
	        long max = 9999999999L;
	        long accountnum = min + ((long)(rand.nextDouble() * (max - min + 1)));
	      
		ac.setAccount_number(accountnum);
		
		acc.save(ac);
		

		List<CutomerandAccount> all = admin.allUsers();

		if (all != null) {
			m.addAttribute("customers", all);
			return "users-accounts";
		} else {
			m.addAttribute("msg", "Not found");
		}

		return "users-accounts";
	}
	
	@PostMapping("/search")
	public String searchByidinuser(@RequestParam String accountId,Model m) {
		int id=Integer.parseInt(accountId);
		
		CutomerandAccount cus=admin.searchByid(id);
		if(cus==null) {
			m.addAttribute("msg","Not found");
			return "/users-accounts";
		}
		else {
			m.addAttribute("customers", cus);
		}
	
		return "/users-accounts";
	}

}
