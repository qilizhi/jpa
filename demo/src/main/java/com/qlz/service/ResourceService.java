/**
 * 
 */
package com.qlz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.ResourceDao;
import com.qlz.entities.Resource;

/**
 * @author qilizhi
 * @date 2016��7��4�� ����6:07:31
 */
@Service
@Transactional
public class ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	public List<Resource> findResourceByRoleId(Long id){
		
	return 	resourceDao.findResourceByRoleId(id);
		
	}
	

}
