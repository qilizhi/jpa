package com.qlz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlz.entities.RoleToAuthority;

/**
 * 
 * @author qilizhi
 * @date 2016年7月4日 下午4:44:57
 */
public interface RoleToAuthorityDao extends BaseDao<RoleToAuthority, Long> {

	/**
	 * @param roleId
	 */
	@Modifying
	@Query("delete from  RoleToAuthority rta where rta.roleId=:roleId ")
	void deleteByRoleId(@Param("roleId")Long roleId);

	/**
	 * @param roleId
	 * @param authorityId
	 */
	@Modifying
	@Query("delete  from  RoleToAuthority rta where rta.roleId=:roleId and rta.authorityId=:authorityId")
	void deleteByRoleIdAndAuthorityId(@Param("roleId")Long roleId, @Param("authorityId")Long authorityId);

	/**
	 * @param roleId
	 * @return
	 */
	List<RoleToAuthority> findByRoleId(Long roleId);

}
