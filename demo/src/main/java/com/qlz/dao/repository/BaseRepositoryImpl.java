/**
 * 
 */
package com.qlz.dao.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.util.BeanUtils;

/**
 * 
 * @author qilizhi
 * @date 2016年7月12日 下午4:32:19
 * @param <T>
 * @param <ID>
 */
/*
 * @Repository
 * 
 * @Transactional(readOnly = true)
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	private final EntityManager entityManager;

	private final JpaEntityInformation<T, ?> jpaEntityInformation;

	/**
	 * @param entityInf
	 *            ormation
	 * @param entityManager
	 */
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.jpaEntityInformation = entityInformation;
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public <S extends T> S merge(S entity) {

		return entityManager.merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qlz.dao.repository.BaseRepository#saveOrUpdate(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public <S extends T> S saveOrUpdate(S entity) {

		ID id = (ID) jpaEntityInformation.getId(entity);
		// TODO Auto-generated method stub
		S tempEntity = null;
		if (id != null) {
			tempEntity = (S) entityManager.find(entity.getClass(), id);
			BeanUtils.copyPropertiesIgnoreNull(entity, tempEntity);
			entity = entityManager.merge(tempEntity);
		} else {
			entity=entityManager.merge(entity);
		}
		// entityManager.flush();
		return entity;
	}

}
