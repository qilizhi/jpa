/**
 * 
 */
package com.qlz.dao.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * @author qilizhi
 * @date 2016年7月7日 下午12:23:51
 */
public class BaseRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}

	private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		private EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		
	
		
		@SuppressWarnings("unused")
		protected Object getTargetRepository(JpaEntityInformation<T,?> entityInformation) {
			return new BaseRepositoryImpl<T, I>(entityInformation, entityManager);
		}

		@SuppressWarnings("unused")
		protected Class<?> getRepositoryClass(RepositoryMetadata metadata) {
			return BaseRepositoryImpl.class;
		}
	}

}
