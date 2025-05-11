package com.example.demo.Db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Loan {
	@Id
    private int loan_id;
    private double cal_emi;
    private double cal_interest;
    private int cust_id;
    private double cal_total;
    private String emp_status;
    private String exis_loan;
    private String income;
    private double interest_rate;
    private double loan_amount;
    private String  loan_type;
    private int loan_year;
    private String mail;
    private String mobile_no;
    private String name;
    private String purpose;
    
    
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public double getCal_emi() {
		return cal_emi;
	}
	public void setCal_emi(double cal_emi) {
		this.cal_emi = cal_emi;
	}
	public double getCal_interest() {
		return cal_interest;
	}
	public void setCal_interest(double cal_interest) {
		this.cal_interest = cal_interest;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public double getCal_total() {
		return cal_total;
	}
	public void setCal_total(double cal_total) {
		this.cal_total = cal_total;
	}
	public String getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}
	public String getExis_loan() {
		return exis_loan;
	}
	public void setExis_loan(String exis_loan) {
		this.exis_loan = exis_loan;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}
	public double getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public int getLoan_year() {
		return loan_year;
	}
	public void setLoan_year(int loan_year) {
		this.loan_year = loan_year;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Loan(int loan_id, double cal_emi, double cal_interest, int cust_id, double cal_total, String emp_status,
			String exis_loan, String income, double interest_rate, double loan_amount, String loan_type, int loan_year,
			String mail, String mobile_no, String name, String purpose) {
		super();
		this.loan_id = loan_id;
		this.cal_emi = cal_emi;
		this.cal_interest = cal_interest;
		this.cust_id = cust_id;
		this.cal_total = cal_total;
		this.emp_status = emp_status;
		this.exis_loan = exis_loan;
		this.income = income;
		this.interest_rate = interest_rate;
		this.loan_amount = loan_amount;
		this.loan_type = loan_type;
		this.loan_year = loan_year;
		this.mail = mail;
		this.mobile_no = mobile_no;
		this.name = name;
		this.purpose = purpose;
	}
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Loan [loan_id=" + loan_id + ", cal_emi=" + cal_emi + ", cal_interest=" + cal_interest + ", cust_id="
				+ cust_id + ", cal_total=" + cal_total + ", emp_status=" + emp_status + ", exis_loan=" + exis_loan
				+ ", income=" + income + ", interest_rate=" + interest_rate + ", loan_amount=" + loan_amount
				+ ", loan_type=" + loan_type + ", loan_year=" + loan_year + ", mail=" + mail + ", mobile_no="
				+ mobile_no + ", name=" + name + ", purpose=" + purpose + "]";
	}
   

    
	
	
}
