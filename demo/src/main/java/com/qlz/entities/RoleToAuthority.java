package com.qlz.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色关系实体
 * @author qilizhi
 * @date 2016年7月4日 下午4:19:57
 */
@Entity
@Table(name="role_to_authority")
public class RoleToAuthority implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer roleId;

    private Integer authorityId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name="authority_id")
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
}