package com.qlz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.entities.Authority;

/**
 * 
 * @author qilizhi
 * @date 2016年7月4日 下午4:42:24
 */
public interface AuthorityDao extends BaseDao<Authority, Long> {

	/**
	 * @param id
	 * @return
	 */
	public List<Authority> findByParentId(Long id);

	/**
	 * @return
	 */
	public List<Authority> findByParentIdIsNull();

	/**
	 * @param auth
	 */
	@Modifying
	@Query("update Authority a set a.name=:auth_name,a.description=:auth_description,a.parentId=auth_parentId where a.id=:auth_id")
	void updateExample(@Param("auth") Authority auth);

}
