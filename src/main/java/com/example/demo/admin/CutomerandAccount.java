package com.example.demo.admin;

import com.example.demo.Db.Account;
import com.example.demo.Db.Customer;

public class CutomerandAccount {

	private Customer cust;
	private Account acc;
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public Account getAcc() {
		return acc;
	}
	public void setAcc(Account acc) {
		this.acc = acc;
	}
	public CutomerandAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CutomerandAccount(Customer cust, Account acc) {
		super();
		this.cust = cust;
		this.acc = acc;
	}
	@Override
	public String toString() {
		return "CutomerandAccount [cust=" + cust + ", acc=" + acc + "]";
	}
		
	
	
}
