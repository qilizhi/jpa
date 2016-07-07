package com.qlz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.Role;
/**
 * 
 * @author qilizhi
 * @date 2016年7月4日 下午4:44:10
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

	/**
	 * @param role
	 */
/*	@Modifying
	@Query("update Role r set r.parentId=:role_parentId ,r.name=:role_name ,r_description=:role_description where r.id=:role_id")
	int update(@Param("role")Role role);*/

}
