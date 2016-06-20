package com.qlz.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="T_LOG")
@Entity
public class Log  {

	private Long id;
	private String userId;
	
	private Date createDate;
	private String content;
	private String operation;
	
	public Log(Long id, String userId, Date createDate, String content, String operation) {
		super();
		this.id = id;
		this.userId = userId;
		this.createDate = createDate;
		this.content = content;
		this.operation = operation;
	}
	public Log() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
