package com.qlz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.Authority;

/**
 * 
 * @author qilizhi
 * @date 
 */
public interface AuthorityDao extends BaseRepository<Authority, Long> {

	/**
	 * @param id
	 * @return
	 */
	public List<Authority> findByParentId(Long id);

	/**
	 * @return
	 */
	public List<Authority> findByParentIdIsNull();



}
