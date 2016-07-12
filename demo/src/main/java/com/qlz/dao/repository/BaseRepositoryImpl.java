/**
 * 
 */
package com.qlz.dao.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author qilizhi
 * @date 2016年7月12日 下午4:32:19
 * @param <T>
 * @param <ID>
 */
/*@Repository
@Transactional(readOnly = true)*/
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {
	private final EntityManager entityManager;

	/**
	 * @param entityInf
	 *            ormation
	 * @param entityManager
	 */
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public <S extends T> S merge(S entity) {
		return entityManager.merge(entity);
	}

}
