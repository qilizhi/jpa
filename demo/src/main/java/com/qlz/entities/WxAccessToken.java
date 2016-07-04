package com.qlz.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="wx_access_token")
public class WxAccessToken{
	private Long id;
	private String accessToken;
	private Long expiresIn;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	private Long weixinPublicId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getWeixinPublicId() {
		return weixinPublicId;
	}
	public void setWeixinPublicId(Long weixinPublicId) {
		this.weixinPublicId = weixinPublicId;
	}
}