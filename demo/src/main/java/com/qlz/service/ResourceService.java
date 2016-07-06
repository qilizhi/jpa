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

import com.qlz.dao.ResourceDao;
import com.qlz.entities.Resource;

/**
 * @author qilizhi
 * @date 2016年7月4日 下午6:07:31
 */
@Service
@Transactional
public class ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	public List<Resource> findResourceByRoleId(Long id) {

		//return resourceDao.find(id);
return null;
	}

	/**
	 * @param resource
	 * @param pageBounds
	 * @return
	 */
	public Page<Resource> getResourcePageList(Resource resource, PageRequest pageBounds) {
		// TODO Auto-generated method stub
		return resourceDao.findAll(Example.of(resource), pageBounds);
	}

	/**
	 * @param id
	 */
	public void deleteByPrimaryKey(Long id) {
		resourceDao.delete(id);
		
	}

	/**
	 * @param resource
	 */
	public void insertSelective(Resource resource) {
		resourceDao.save(resource);
		
	}

	/**
	 * @param resource
	 */
	public void updateByPrimaryKeySelective(Resource resource) {
		resourceDao.update(resource);
		
	}

	/**
	 * @param idArray
	 */
	public void deleteResourceBitch(String[] idArray) {
		resourceDao.findByIdIn(idArray);
		
	}

	/**
	 * @param id
	 * @return
	 */
	public Resource selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return resourceDao.findOne(id);
	}

	/**
	 * @param resource
	 * @return
	 */
	public List<Resource> getResourceList(Resource resource) {
		// TODO Auto-generated method stub
		return resourceDao.findAll(Example.of(resource));
	}

	/**
	 * @param authorityId
	 * @param pageBounds
	 * @return
	 */
	public Page<Resource> selectResourceByExample(Long authorityId, PageRequest pageBounds) {
		// TODO Auto-generated method stub
		//return resourceDao.find(authorityId, pageBounds);
		return null;
	}

}
