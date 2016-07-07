package com.qlz.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.AuthorityToResource;
import com.qlz.entities.Resource;

/**
 * 
 * @author qilizhi
 * @date 2016年7月4日 下午4:42:28
 */
public interface AuthorityToResourceDao extends BaseRepository<AuthorityToResource, Long> {



	/**
	 * @param authorityId
	 */
	/*
	 * @Modifying
	 * 
	 * @Query(
	 * "delete atr from AuthorityToResource atr where atr.authorityId=:authId")
	 */
	public List<AuthorityToResource> findByAuthorityIdOrResourceId(Long authorityId,Long resourceId);
	void deleteByAuthorityId(Long authorityId);

}
