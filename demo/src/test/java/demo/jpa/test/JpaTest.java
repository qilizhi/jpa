package demo.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qlz.dao.CustomerDao;
import com.qlz.entities.Customer;

public class JpaTest {
	Logger logger = LoggerFactory.getLogger(JpaTest.class);

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	ApplicationContext tx;

	@Before
	public void init() {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("demo");

		tx = new ClassPathXmlApplicationContext("applicationContext.xml");
		entityManagerFactory = tx.getBean(EntityManagerFactory.class);
		entityManager = entityManagerFactory.createEntityManager();
		/*
		 * transaction = entityManager.getTransaction(); transaction.begin();
		 */
	}

	@After
	public void destroy() {
		/*
		 * transaction.commit(); entityManager.close();
		 * entityManagerFactory.close();
		 */
	}

	// 可以使用 JPQL 完成 UPDATE 和 DELETE 操作.
	@Test
	public void testExecuteUpdate() {

		Customer d1 = entityManager.find(Customer.class, 1);
		Customer d2 = entityManager.find(Customer.class, 1);
		logger.info("结果：" + (d1 == d2));
		/*
		 * String jpql = "UPDATE Customer c SET c.lastName = ? WHERE c.id = ?";
		 * Query query = entityManager.createQuery(jpql).setParameter(1,
		 * "YYY").setParameter(2, 12);
		 * 
		 * query.executeUpdate();
		 */
	}

	@Test
	public void TestCache() {

		/*
		 * String jpql = "FROM Customer c WHERE c.age > ?"; Query query =
		 * entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE,
		 * true);
		 * 
		 * // 占位符的索引是从 1 开始 query.setParameter(1, 1); List<Customer> customers =
		 * query.getResultList(); System.out.println(customers.size());
		 * 
		 * query =
		 * entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE,
		 * true);
		 * 
		 * // 占位符的索引是从 1 开始 query.setParameter(1, 1); customers =
		 * query.getResultList(); System.out.println(customers.size());
		 */

		EntityManager em1 = entityManagerFactory.createEntityManager();
		Customer d1 = em1.find(Customer.class, 1); // find id为1的对象
		logger.info(d1.getLastName());
		em1.close();

		EntityManager em2 = entityManagerFactory.createEntityManager();
		Customer d2 = em2.find(Customer.class, 1); // find id为1的对象
		logger.info(d2.getLastName());
		em2.close();

	}

}
