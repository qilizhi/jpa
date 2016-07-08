package com.qlz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="S_Province")
public class Province {
	
	private Long  provinceId;
	private String provinceName;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProvinceID")
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="ProvinceName")
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
