package com.example.demo.Db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {

	@Id
	private int cust_id;
	private String ac_type;
	private long account_number;
	private long balance;
	private String password;
	
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getAc_type() {
		return ac_type;
	}
	public void setAc_type(String ac_type) {
		this.ac_type = ac_type;
	}
	public long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(int cust_id, String ac_type, long account_number, long balance, String password) {
		super();
		this.cust_id = cust_id;
		this.ac_type = ac_type;
		this.account_number = account_number;
		this.balance = balance;
		this.password = password;
	}
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [cust_id=" + cust_id + ", ac_type=" + ac_type + ", account_number=" + account_number
				+ ", balance=" + balance + ", password=" + password + "]";
	}
	
	
	
}
