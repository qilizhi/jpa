package com.qlz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.CustomerDao;

@Service
@Transactional
public class CustomerService {
	
	
	@Autowired
	private CustomerDao customerDao;
	
	
	public void updateCustomer(String lastName,Integer id){
		
		customerDao.updateByLastNameById(id, lastName);
	}

	
	

}
