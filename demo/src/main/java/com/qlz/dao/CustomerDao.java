package com.qlz.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.entities.Customer;


public interface CustomerDao extends PagingAndSortingRepository<Customer, Integer>
// , QueryByExampleExecutor<Customer>
{
	public List<Customer> findByLastName(String lastName);

	public Page<Customer> findByLastName(String lastName, Pageable page);

	/* �Զ����� */
	@Query("select c from Customer c where c.email=?1")
	/* ʹ�ò������� */
	public List<Customer> getListByEmail(String email);

	@Query("select c from Customer c where c.email=:email and c.lastName=:lastName")
	public List<Customer> getListByEmailAndLastName(@Param("email") String email, @Param("lastName") String lastName);

	public Customer findOneByEmail(String email);

	// �޸����� �� modifying

	@Modifying
	@Query("update Customer c set c.lastName=:lastName where c.id=:id")
	public int updateByLastNameById(@Param("id") Integer id, @Param("lastName") String lastName);

}
