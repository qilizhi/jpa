package com.qlz.dao;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.User;

public interface UserDao extends BaseRepository<User, Long> {

	/**
	 * @param userNo
	 * @return
	 */
//	@Query("select DISTINCT(r) from Role r,UserToRole utr where r.id=utr.roleId and utr.userNo=:userNo")
	//List<Role> findRoleByUserNo(@Param("userNo") String userNo);

	/**
	 * @param userNo
	 * @return
	 */
	//@Query("select DISTINCT(R) from Resource R ,AuthorityToResource atr ,RoleToAuthority rta, UserToRole utr where R.id=atr.resourceId and atr.authorityId=rta.authorityId and rta.roleId=utr.roleId and utr.userNo=:userNo")
	//List<Resource> findResourcesByUserNo(@Param("userNo")String userNo);

}
