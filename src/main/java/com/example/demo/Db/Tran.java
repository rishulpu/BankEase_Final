package com.example.demo.Db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tran {

	@Id
	private int trans_id;
	private String desce;
	private int credit;
	private int cust_id;
	private String date;
	private int deposite;
	private int total_balance;
	
	
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	public String getDesce() {
		return desce;
	}
	public void setDesce(String desce) {
		this.desce = desce;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDeposite() {
		return deposite;
	}
	public void setDeposite(int deposite) {
		this.deposite = deposite;
	}
	public int getTotal_balance() {
		return total_balance;
	}
	public void setTotal_balance(int total_balance) {
		this.total_balance = total_balance;
	}
	@Override
	public String toString() {
		return "Tran [trans_id=" + trans_id + ", desce=" + desce + ", credit=" + credit + ", cust_id=" + cust_id
				+ ", date=" + date + ", deposite=" + deposite + ", total_balance=" + total_balance + "]";
	}
	public Tran(int trans_id, String desce, int credit, int cust_id, String date, int deposite, int total_balance) {
		super();
		this.trans_id = trans_id;
		this.desce = desce;
		this.credit = credit;
		this.cust_id = cust_id;
		this.date = date;
		this.deposite = deposite;
		this.total_balance = total_balance;
	}
	public Tran() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
