package com.qlz.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="wx_event_push_article")
public class WxEventPushArticle{
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	private String pushEventCode;
	private String description;
	private String title;
	private String picUrl;
	private String url;
	private Long seq;
	private Long weixinPublicId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPushEventCode() {
		return pushEventCode;
	}
	public void setPushEventCode(String pushEventCode) {
		this.pushEventCode = pushEventCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public Long getWeixinPublicId() {
		return weixinPublicId;
	}
	public void setWeixinPublicId(Long weixinPublicId) {
		this.weixinPublicId = weixinPublicId;
	}
}