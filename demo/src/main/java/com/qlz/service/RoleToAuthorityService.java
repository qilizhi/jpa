/**
 * 
 */
package com.qlz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.RoleToAuthorityDao;
import com.qlz.entities.RoleToAuthority;

/**
 * @author qilizhi
 * @date 2016年7月5日 下午2:33:55
 */
@Service
@Transactional
public class RoleToAuthorityService {

	@Autowired
	private RoleToAuthorityDao roleToAuthorityDao;

	/**
	 * @param roleId
	 * @param authorityIdsList
	 */
	public void updateByDelete(Long roleId, List<Long> authorityIdsList) {

         roleToAuthorityDao.deleteByRoleId(roleId);
         List<RoleToAuthority> rtas=new ArrayList<RoleToAuthority>();
         for(Long authorityId:authorityIdsList){
        	 RoleToAuthority rta=new RoleToAuthority();
        	 rta.setRoleId(roleId);
        	 rta.setAuthorityId(authorityId);
        	 rtas.add(rta);
         }

         roleToAuthorityDao.save(rtas);
	}

	/**
	 * @param roleId
	 * @param authorityIdsList
	 */
	public void batDelete(Long roleId, List<Long> authorityIdsList) {
		// TODO Auto-generated method stub
		for(Long authorityId:authorityIdsList){
		roleToAuthorityDao.deleteByRoleIdAndAuthorityId(roleId,authorityId);
		}
	}

	/**
	 * @param roleId
	 * @return
	 */
	public List<RoleToAuthority> selectByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return roleToAuthorityDao.findByRoleId(roleId);
	}

	
	
}
