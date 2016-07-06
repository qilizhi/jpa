/**
 * 
 */
package com.qlz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.UserDao;
import com.qlz.entities.Resource;
import com.qlz.entities.Role;
import com.qlz.entities.User;

/**
 * @author qilizhi
 * @date 2016年7月4日 下午6:20:24
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;

	/**
	 * @param userInfo
	 * @return
	 */
	public List<User> findByExample(User userInfo) {
		// TODO Auto-generated method stub
		return userDao.findAll(Example.of(userInfo));
	}

	/**
	 * @param userNo
	 * @return
	 */
	public List<Role> getRolesByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return userDao.findRoleByUserNo(userNo);
	}

	/**
	 * @param userNo
	 * @return
	 */
	public List<Resource> getResourcesByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return userDao.findResourcesByUserNo(userNo);
	}
	
	
	

}
