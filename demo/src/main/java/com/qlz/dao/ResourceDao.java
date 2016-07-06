
package com.qlz.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.entities.Authority;
import com.qlz.entities.Resource;

/**
 * @author qilizhi
 * @date 2016年7月4日 下午4:42:07
 */
public interface ResourceDao extends BaseDao<Resource, Long> {

	
	/**
	 * @param authorityId
	 * @param pageBounds
	 * @return
	 */
	
	@Query(name="select R from Resource R ,AuthorityToResource atr where atr.resourceId=R.id and atr.authorityId=:authId")
	public Page<Resource> findById(@Param("auth") Long authority,@Param("page")Pageable pageBounds);
	/**
	 * @param id
	 * @return
	 */
	@Query(name = "select r from Resource r,RoleToAuthority rta,AuthorityToResource atr where r.id=atr.resourceId and atr.authorityId=rta.authorityId and rta.roleId and rta.roleId=?")
	public List<Resource> findById(Long id);

	/**
	 * @param resource
	 */
	@Query("update Resource r set r.path=:res_path,r.name=res_name,r.description=:res_description,r.flag=res_flag where r.id=:res_id")
	public int update(@Param("res") Resource resource);

	/**
	 * @param idArray
	 */
	public void findByIdIn(String[] idArray);

}
