package com.qlz.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author qilizhi
 * @date 2016年7月4日 下午4:42:34
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
