
package com.qlz.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.Resource;

/**
 * @author qilizhi
 * @date 2016��7��4�� ����4:42:07
 */
public interface ResourceDao extends BaseRepository<Resource, Long> {

	/**
	 * @param authorityId
	 * @param pageBounds
	 * @return
	 */

	/**
	 * @param idArray
	 */
	public List<Resource> findByIdIn(String[] idArray);
	public Set<Resource> findByIdIn(List<Long> ids);

	/**
	 * @param id
	 * @return
	 */
	@Query("select R from Resource R join R.authorities RA join RA.roles AR where AR.id=:roleId")
	public List<Resource> getResourceByRoleId(@Param("roleId")Long roleId);

	@Query("select R from Resource R join R.authorities RA where RA.id=:authorityId")
	public List<Resource> getResourceByAuthoriytId(@Param("authorityId") Long authorityId);

	/**
	 * @param authorityId
	 * @param page
	 * @return
	 */
	@Query("select R from Resource R join R.authorities RA where RA.id=:authorityId")
	public Page<Resource> getResourceByAuthoriytId(@Param("authorityId")Long authorityId, Pageable page);
	
}
