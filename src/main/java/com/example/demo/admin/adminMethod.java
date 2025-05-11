package com.example.demo.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Db.Account;
import com.example.demo.Db.AccountRepo;
import com.example.demo.Db.Cust_Repo;
import com.example.demo.Db.Customer;
import com.example.demo.Service.CustomerMethod;

@Service
public class adminMethod {

	@Autowired
	private Cust_Repo cust;
	@Autowired
	private AccountRepo acc;
	@Autowired
	private CustomerMethod cm;

	public CutomerandAccount searchCustandAccByID(int id) {
		Account acc = cm.searchAccoutById(id);
		Customer c = cm.searchCustomerById(id);

		if (acc == null) {
			return null;
		}
		CutomerandAccount res = new CutomerandAccount();
		res.setAcc(acc);
		res.setCust(c);

		return res;
	}

	public CutomerandAccount searchByemail(String email) {

		Customer cc = cust.findByEmail(email);
		CutomerandAccount custo = new CutomerandAccount();
		int id = 0;
		Account acc = null;
		if (cc != null) {
			id = cc.getId();
			acc = cm.searchAccoutById(id);
			custo.setAcc(acc);
			custo.setCust(cc);
		}
		if (custo.getAcc() != null) {
			return custo;
		}
		System.out.println(custo);

		return null;
	}

	public List<CutomerandAccount> searchByname(String name) {

		List<CutomerandAccount> res = new ArrayList<>();

		Iterable<Customer> cus = cust.findAll();
		for (Customer cc : cus) {
			String nn = cc.getName();

			if (nn.equalsIgnoreCase(name)) {

				Account acc = cm.searchAccoutById(cc.getId());
				res.add(new CutomerandAccount(cc, acc));
			}
		}
		return res;
	}

	public List<CutomerandAccount> allUsers() {

		List<CutomerandAccount> res = new ArrayList<>();

		Iterable<Customer> cus = cust.findAll();
		for (Customer cc : cus) {
			
			Account acc = cm.searchAccoutById(cc.getId());
			res.add(new CutomerandAccount(cc, acc));

		}
		if(!res.isEmpty()) {
		return res;
		}
		else{
			return null;
		}
	}
	
	public CutomerandAccount searchByid(int id) {
		Account ac=cm.searchAccoutById(id);
		Customer cus=cm.searchCustomerById(id);
		
		if(ac==null){
			return null;
		}
		else {
			return new CutomerandAccount(cus,ac);
		}
	}

}
