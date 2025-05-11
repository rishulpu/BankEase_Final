package com.example.demo.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Db.Account;
import com.example.demo.Db.AccountRepo;
import com.example.demo.Db.Cust_Repo;
import com.example.demo.Db.Customer;
import com.example.demo.Db.Loan;
import com.example.demo.Db.LoanRepo;
import com.example.demo.Db.Tran;
import com.example.demo.Db.Trans_Repo;
import com.example.demo.loan.LoanModule;
import com.example.demo.mixClass.TransAndAccount;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerMethod {
	
	@Autowired
    Cust_Repo cust;
	
	@Autowired
	AccountRepo acc;
	
	@Autowired
	Trans_Repo tran;
	
	@Autowired
	LoanRepo loan;
	
	public int returnIdfromEmailandPass(String email,String pass) {
		
	
		Customer cs=cust.findByEmail(email);
		
		Optional<Account> ac=null;
		String dbEmail=null;
		String dbPass=null;
		if(cs!=null) {
			ac=acc.findById(cs.getId());
			Account acount=ac.get();
			dbEmail=cs.getEmail();
			dbPass=acount.getPassword();
			if(dbEmail.equalsIgnoreCase(email)&&dbPass.equals(pass)) {
				System.out.println("Mathc");
				System.out.println(cs.getId());
				return cs.getId();
			}
			else {
				System.out.println("Not match");
				return 0;
			}
		}
		else {
			return 0;
		}
	   
		}
		
	
	

	public Account searchAccoutById(int id) {
		Iterable<Account> ac=acc.findAll();
		for(Account a:ac) {
			if(a.getCust_id()==id) {
				return a;
			}
		}
		return null;
	}
	
	public Customer searchCustomerById(int id) {
		Iterable<Customer> cu=cust.findAll();
		for(Customer cc:cu) {
			if(cc.getId()==id) {
				return cc;
			}
		}
		return null;
	}
	
	public List<Tran> searchTranById(int id){
		List<Tran> tt=new ArrayList<>();
		Iterable<Tran> trans= tran.findAll();
		for(Tran t:trans) {
			if(t.getCust_id()==id) {
				tt.add(t);
			}
		}
		return tt;
	}
	
	
	
	
	
	public TransAndAccount TransactionWithdeposite(int depositeAmount,String description,int id ) {
	
		Optional<Customer> c=cust.findById(id);
		Customer customer=c.get();
		
		Optional<Account> a=acc.findById(id);
		Account account=a.get();
		
	    long intialBal= account.getBalance();
	    long totalBal=depositeAmount+intialBal;
	    
	    account.setBalance(totalBal);
	    acc.save(account);
	    
	    Tran t=new Tran();
	    t.setCust_id(id);
	    LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);
	    t.setDate(date);
	    t.setDeposite(depositeAmount);
	    t.setCredit(0);
	    t.setDesce(description);
	    int ttb=(int) totalBal;
	    t.setTotal_balance(ttb);
	    Random rand = new Random();
        int tid = 10000 + rand.nextInt(90000);
	    t.setTrans_id(tid);
	    
	    tran.save(t);
	    
	    Iterable<Tran> list=tran.findAll();
	    List<Tran> res=new ArrayList<>();
	    for(Tran tt:list) {
	    	if(tt.getCust_id()==id) {
	    	res.add(tt);
	    	}
	    }
	    
	    TransAndAccount result=new TransAndAccount();
	    
	    result.setAc(account);
	    result.setTran(res);
	    
	   return result;
	}
	
	
	public TransAndAccount TransactionWithWithdraw(int withdrawAmount,String description,int id ) {
		
		Optional<Customer> c=cust.findById(id);
		Customer customer=c.get();
		
		Optional<Account> a=acc.findById(id);
		Account account=a.get();
		
	    long intialBal= account.getBalance();
	    
	    if(intialBal<withdrawAmount) {
	    	return null;
	    }
	    long totalBal=intialBal-withdrawAmount;
	    
	    account.setBalance(totalBal);
	    acc.save(account);
	    
	    Tran t=new Tran();
	    t.setCust_id(id);
	    LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);
	    t.setDate(date);
	    t.setDeposite(0);
	    t.setCredit(withdrawAmount);
	    t.setDesce(description);
	    int ttb=(int) totalBal;
	    t.setTotal_balance(ttb);
	    Random rand = new Random();
        int tid = 10000 + rand.nextInt(90000);
	    t.setTrans_id(tid);
	    
	    tran.save(t);
	    
	    Iterable<Tran> list=tran.findAll();
	    List<Tran> res=new ArrayList<>();
	    for(Tran tt:list) {
	    	if(tt.getCust_id()==id) {
		    	res.add(tt);
		    	}
	    	
	    }
	    
	    TransAndAccount result=new TransAndAccount();
	    
	    result.setAc(account);
	    result.setTran(res);
	    
	   return result;
	}
	
	public boolean changePass(int id, String oldPass,String newPass) {
		Optional<Account>acount=acc.findById(id);
		Account ac=acount.get();
		
		String tempPass=ac.getPassword();
		if(oldPass.equals(tempPass)) {
			ac.setPassword(newPass);
			acc.save(ac);
			return true;
		}
		
		return false;
	}
	
	public boolean fundTransfer(long amount,String cust_name,long accountNo) {
		Iterable<Account> icr=acc.findAll();
		for(Account ac:icr) {
			if(ac.getAccount_number()==accountNo) {
				Account res=ac;
				Long bal=res.getBalance();
				Long finBal=bal+amount;
				res.setBalance(finBal);
				acc.save(res);
				return true;
			}
		}
		return false;
	}
	
	public Loan searchById(int id) {
		
		Iterable<Loan> ll=loan.findAll();
		
		for(Loan l:ll) {
			if(l.getCust_id()==id) {
				return l;
			}
		}
		
		return null;
	}
	
	
	
}
