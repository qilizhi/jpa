package com.qlz.dao;

import java.util.List;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.Authority;
import com.qlz.entities.Resource;

/**
 * 
 * @author qilizhi
 * @date 2016年7月24日 下午5:43:58
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
	public List<Authority> findByIdIn(String[] idArray);
	public List<Authority> findByIdIn(List<Long> ids);



}
