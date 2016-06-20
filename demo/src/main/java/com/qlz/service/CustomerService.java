package com.qlz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.CustomerDao;
import com.qlz.entities.Customer;

@Service
@Transactional
@CacheConfig(cacheNames = "customerService", keyGenerator = "keyGenerator")
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public void updateCustomer(String lastName, Integer id) {

		customerDao.updateByLastNameById(id, lastName);
	}

	@Cacheable
	public List<Customer> list() {
		return customerDao.findAll();

	}

}
