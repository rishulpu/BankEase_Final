package com.example.demo.mixClass;

import java.util.List;

import com.example.demo.Db.Account;
import com.example.demo.Db.Tran;


public class TransAndAccount {
	
	private Account ac;
	private List<Tran> tran;
	public Account getAc() {
		return ac;
	}
	public void setAc(Account ac) {
		this.ac = ac;
	}
	public List<Tran> getTran() {
		return tran;
	}
	public void setTran(List<Tran> tran) {
		this.tran = tran;
	}
	public TransAndAccount(Account ac, List<Tran> tran) {
		super();
		this.ac = ac;
		this.tran = tran;
	}
	public TransAndAccount() {
		super();
		
	}
	@Override
	public String toString() {
		return "TransAndAccount [ac=" + ac + ", tran=" + tran + "]";
	}
	
	

}
