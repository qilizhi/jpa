/**
 * 
 */
package com.qlz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.UserDao;

/**
 * @author qilizhi
 * @date 2016��7��4�� ����6:20:24
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	

}
