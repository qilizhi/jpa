/**
 * 
 */
package com.qlz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.RoleDao;
import com.qlz.entities.Role;

/**
 * @author qilizhi
 * @date 2016��7��4�� ����5:13:44
 */
@Service
@Transactional
public class RoleService {

	private RoleDao roleDao;

	public List<Role> findAll() {

		return roleDao.findAll();

	}

}
