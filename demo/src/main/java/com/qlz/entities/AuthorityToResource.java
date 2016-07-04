package com.qlz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authority_to_resource")
public class AuthorityToResource {
	private Long id;
	private Integer authorityId;
	private Integer resourceId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="authority_id")
	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	@Column(name="resource_id")
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
}