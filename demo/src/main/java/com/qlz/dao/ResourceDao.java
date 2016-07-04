
package com.qlz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.qlz.entities.Resource;

/**
 * @author qilizhi
 * @date 2016年7月4日 下午4:42:07
 */
public interface ResourceDao extends BaseDao<Resource, Long> {

	/**
	 * @param id
	 * @return
	 */
	@Query(name = "select r from Resource r,RoleToAuthority rta,AuthorityToResource atr where r.id=atr.resourceId and atr.authorityId=rta.authorityId and rta.roleId and rta.roleId=?")
	List<Resource> findResourceByRoleId(Long id);

}
