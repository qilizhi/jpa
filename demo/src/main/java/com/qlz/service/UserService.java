/**
 * 
 */
package com.qlz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.constant.Const;
import com.qlz.dao.UserDao;
import com.qlz.entities.Resource;
import com.qlz.entities.Role;
import com.qlz.entities.User;
import com.qlz.util.Digests;
import com.qlz.util.Encodes;

/**
 * @author qilizhi
 * @date 2016
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
		// return userDao.findRoleByUserNo(userNo);
		return null;
	}

	/**
	 * @param userNo
	 * @return
	 */
	public List<Resource> getResourcesByUserNo(String userNo) {
		// TODO Auto-generated method stub
		// return userDao.findResourcesByUserNo(userNo);
		return null;
	}

	/**
	 * @param id
	 * @return
	 */
	public User getUserInfoByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return userDao.getOne(id);
	}

	
	private void entryptPassword(User user) {
		if (user == null || user.getPassword() == null)
			return;
		byte[] salt = Digests.generateSalt(Const.SALT_SIZE);
		//String ensalt=Encodes.encodeHex(salt);
		//byte[] desalt=Encodes.decodeHex(ensalt);
	//	System.out.println(salt.equals(desalt));
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Const.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public void resetPassword(User user) {
		entryptPassword(user);
		userDao.saveOrUpdate(user);

 	}

	/**
	 * @param userInfo
	 * @param pageBounds
	 * @return
	 */
	public Page<User> getUserInfoPageList(User user, PageRequest pageBounds) {
		// TODO Auto-generated method stub
		return userDao.findAll(Example.of(user), pageBounds);
	}


}
