package com.example.demo.Db;

import org.springframework.data.repository.CrudRepository;

public interface Cust_Repo extends CrudRepository<Customer, Integer>{

	Customer findByEmail(String email);
	
}
