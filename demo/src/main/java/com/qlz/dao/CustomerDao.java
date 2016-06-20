package com.qlz.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.qlz.entities.Customer;

/**
 * 
 * 
 * @author QiQi-04-PC
 *
 *
 *         1. Repository ��һ���սӿ�. ����һ����ǽӿ� 2. �����Ƕ���Ľӿڼ̳��� Repository, ��ýӿڻᱻ IOC
 *         ����ʶ��Ϊһ�� Repository Bean. ���뵽 IOC ������. ���������ڸýӿ��ж�������һ���淶�ķ���.
 * 
 *         3. ʵ����, Ҳ����ͨ�� @RepositoryDefinition ע��������̳� Repository �ӿ�
 *
 * 
 *         �� Repository �ӽӿ����������� 1. �������������. ����Ҫ����һ���Ĺ淶 2. ��ѯ������ find | read |
 *         get ��ͷ 3. �漰������ѯʱ�������������������ؼ������� 4. Ҫע����ǣ���������������ĸ��д�� 5. ֧�����Եļ�����ѯ.
 *         ����ǰ���з�������������, ������ʹ��, ����ʹ�ü�������. ����Ҫʹ�ü�������, ������֮��ʹ�� _ ��������.
 *
 */

public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer>,QueryByExampleExecutor<Customer>
{
	public List<Customer> findByLastName(String lastName);

	public Page<Customer> findByLastName(String lastName, Pageable page);

	/* �Զ����� */
	@Query("select c from Customer c where c.email=?1")
	/* ʹ�ò������� */
	public List<Customer> getListByEmail(String email);

	// WHERE lastName LIKE %? AND id < ?
	List<Customer> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

	// SpringData ������ռλ������� %%.
	@Query("select c from Customer c where c.email=:email or c.lastName like %:lastName%")
	public List<Customer> getListByEmailAndLastName(@Param("email") String email, @Param("lastName") String lastName);

	//���ñ������ ֱ�Ӳ�����
	@Query(value = "SELECT count(id) FROM jpa_customer", nativeQuery = true)
	long getTotalCount();

	public Customer findOneByEmail(String email);

	// �޸����� �� modifying
	// ����ͨ���Զ���� JPQL ��� UPDATE �� DELETE ����. ע��: JPQL ��֧��ʹ�� INSERT
	// �� @Query ע���б�д JPQL ���, ������ʹ�� @Modifying ��������. ��֪ͨ SpringData, ����һ��
	// UPDATE �� DELETE ����
	// UPDATE �� DELETE ������Ҫʹ������, ��ʱ��Ҫ���� Service ��. �� Service ��ķ���������������.
	// Ĭ�������, SpringData ��ÿ��������������, ������һ��ֻ������. ���ǲ�������޸Ĳ���!
	@Modifying
	@Query(value = "update Customer c set c.lastName=:lastName where c.id=:id")
	public int updateByLastNameById(@Param("id") Integer id, @Param("lastName") String lastName);

}
