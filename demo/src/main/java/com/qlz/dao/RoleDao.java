package com.qlz.dao;

import java.util.List;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.Role;
/**
 * 
 * @author qilizhi
 * @date 2016年7月24日 下午5:43:23
 */
public interface RoleDao extends BaseRepository<Role, Long> {


	/**
	 * @param id
	 * @return
	 */
	List<Role> findByParentId(Long id);

	/**
	 * @return
	 */
	List<Role> findByParentIdIsNull();


}
