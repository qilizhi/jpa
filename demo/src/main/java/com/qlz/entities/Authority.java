package com.qlz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private List<Authority> children = new ArrayList<Authority>();
	private String uri;
	private List<Role> roles = new ArrayList<Role>();
	private List<Resource> resources = new ArrayList<Resource>();

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
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "authority_resource", joinColumns = {
			@JoinColumn(name = "authority_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "resource_id", referencedColumnName = "id") })
	// @Transient
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER )
	@JoinColumn(name="parent_id")
	@JsonBackReference
	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}

	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="parent",fetch=FetchType.EAGER)
	public List<Authority> getChildren() {
		return children;
	}

	public void setChildren(List<Authority> children) {
		this.children = children;
	}

}