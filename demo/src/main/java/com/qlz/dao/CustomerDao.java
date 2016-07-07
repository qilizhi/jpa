package com.qlz.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.qlz.dao.repository.BaseRepository;
import com.qlz.entities.Customer;

/**
 * 
 * 
 * @author QiQi-04-PC
 *
 *
 *         1. Repository 是一个空接口. 即是一个标记接口 2. 若我们定义的接口继承了 Repository, 则该接口会被 IOC
 *         容器识别为一个 Repository Bean. 纳入到 IOC 容器中. 进而可以在该接口中定义满足一定规范的方法.
 * 
 *         3. 实际上, 也可以通过 @RepositoryDefinition 注解来替代继承 Repository 接口
 *
 * 
 *         在 Repository 子接口中声明方法 1. 不是随便声明的. 而需要符合一定的规范 2. 查询方法以 find | read |
 *         get 开头 3. 涉及条件查询时，条件的属性用条件关键字连接 4. 要注意的是：条件属性以首字母大写。 5. 支持属性的级联查询.
 *         若当前类有符合条件的属性, 则优先使用, 而不使用级联属性. 若需要使用级联属性, 则属性之间使用 _ 进行连接.
 *
 */

public interface CustomerDao extends BaseRepository<Customer, Integer> {
	public List<Customer> findByLastName(String lastName);

	public Page<Customer> findByLastName(String lastName, Pageable page);

	/* 自定函数 */
	@Query("select c from Customer c where c.email=?1")
	/* 使用参数名。 */
	public List<Customer> getListByEmail(String email);

	 @Query("select c from Customer c where c.email=?1")
	/* 使用参数名。 */
	//@Query(name = "select r from Resource r where r.id=?1")
	public List<Customer> getListByEa(String email);

	/*
	 * @Query(name = "select r from Resource r where r.id=:roleId") public
	 * List<Resource> getListByR(@Param("roleId")Long roleId);
	 */

	// WHERE lastName LIKE %? AND id < ?
	List<Customer> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

	// SpringData 允许在占位符上添加 %%.
	@Query("select c from Customer c where c.email=:email or c.lastName like %:lastName%")
	public List<Customer> getListByEmailAndLastName(@Param("email") String email, @Param("lastName") String lastName);

	// 调用本地语句 直接操作表。
	@Query(value = "SELECT count(id) FROM jpa_customer", nativeQuery = true)
	long getTotalCount();

	public Customer findOneByEmail(String email);

	// 修改数据 加 modifying
	// 可以通过自定义的 JPQL 完成 UPDATE 和 DELETE 操作. 注意: JPQL 不支持使用 INSERT
	// 在 @Query 注解中编写 JPQL 语句, 但必须使用 @Modifying 进行修饰. 以通知 SpringData, 这是一个
	// UPDATE 或 DELETE 操作
	// UPDATE 或 DELETE 操作需要使用事务, 此时需要定义 Service 层. 在 Service 层的方法上添加事务操作.
	// 默认情况下, SpringData 的每个方法上有事务, 但都是一个只读事务. 他们不能完成修改操作!
	@Modifying
	@Query(value = "update Customer c set c.lastName=:lastName where c.id=:id")
	public int updateByLastNameById(@Param("id") Integer id, @Param("lastName") String lastName);

	// spring-data-jpa默认继承实现的一些方法，实现类为
	// SimpleJpaRepository。
	// 该类中的方法不能通过@QueryHint来实现查询缓存。
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Customer> findAll();

	@Query("from Customer")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<Customer> findAllCached();

	@Query("select t from Customer t where t.name = ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Customer findCustomerByName(String name);

}
