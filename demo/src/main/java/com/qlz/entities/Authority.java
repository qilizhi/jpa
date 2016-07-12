package com.qlz.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 权限树
 * 
 * @author qilizhi
 * @date 2016年7月8日 下午3:34:34
 */
@Entity
@Table
@DynamicInsert(true)
@DynamicUpdate(true)
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private Authority parent;
	private Set<Authority> children = new LinkedHashSet<Authority>();
	private String uri;
	private Set<Role> roles = new HashSet<Role>();
	private Set<Resource> resources = new HashSet<Resource>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "authorities")
	// @Transient
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "authority_resource", joinColumns = {
			@JoinColumn(name = "authority_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "resource_id", referencedColumnName = "id") })
	// @Transient
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER )
	@JoinColumn(name="parent_id")
	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent",fetch=FetchType.EAGER)
	public Set<Authority> getChildren() {
		return children;
	}

	public void setChildren(Set<Authority> children) {
		this.children = children;
	}

}