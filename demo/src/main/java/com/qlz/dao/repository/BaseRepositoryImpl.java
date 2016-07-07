/**
 * 
 */
package com.qlz.dao.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qilizhi
 * @date 2016年7月7日 下午12:14:03
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	/**
	 * @param entityInf
	 *            ormation
	 * @param entityManager
	 */
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		// TODO Auto-generated constructor stub
	}

/*	@Transactional
	public <S extends T> S saveOrUpdate(S entity) {
		EntityManager em =super.;
		if () {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}

	}*/

}
