package com.qlz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.entities.Authority;
/**
 * 
 * @author qilizhi
 * @date 2016��7��4�� ����4:42:24
 */
public interface AuthorityDao extends BaseDao<Authority, Long> {

	/**
	 * @param userNo
	 * @return
	 */
	List<Authority> findAuhtoritysByUserNo(String userNo);

	/**
	 * @param id
	 * @return
	 */
	List<Authority> findByParentId(Long id);

	/**
	 * @return
	 */
	List<Authority> findByParentIdIsNull();

	/**
	 * @param auth
	 */
	@Modifying
	@Query("update Authority a set a.name=:auth.name,a.description=:auth.description,a.parentId=auth.parentId")
	void updateByIdExample(@Param("auth")Authority auth);

}
