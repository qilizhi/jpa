package com.qlz.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author qilizhi
 * @date 2016��7��4�� ����4:01:46
 */
@Entity
@Table
public class Authority implements Serializable{
    /**
     * �û�Ȩ�ޱ�
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String description;

    private Long parentId;

    private String uri;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
 
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


}