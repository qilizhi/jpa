package demo.jpa.test;

import static org.springframework.data.domain.ExampleMatcher.matching;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.qlz.dao.CustomerDao;
import com.qlz.dao.ResourceDao;
import com.qlz.entities.Authority;
import com.qlz.entities.Customer;
import com.qlz.entities.Resource;
import com.qlz.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp,src/main/resources")
@ContextHierarchy({
		@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:applicationContext.xml" }) })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** ��������ع���Ҫ������һ���������ݾ�û�����񲻺ܱ��ߣ�ע�⣺��������ʱ��ע������������ع� **/
@Rollback(value=true)
public class SpringMVCTest {

	Logger logger = LoggerFactory.getLogger(SpringMVCTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private CustomerService customerService;

	// 根据具体要测试的controller 测试
	@Before
	public void setUp() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	// @Test
	// public void testSearch() throws Exception {
	//
	// ResultActions result =
	// mockMvc.perform(MockMvcRequestBuilders.get("/v1/guide/9"));
	// MvcResult mrs = result.andDo(MockMvcResultHandlers.print()).andReturn();
	// // 返回状�?�?
	// int resultStr = mrs.getResponse().getStatus();
	//
	// // 如果返回状�?相等 就�?�?
	// Assert.assertEquals(200, resultStr);
	// }

	@Test
	public void testList() throws Exception {

		Customer cust = new Customer();
		cust.setAge(200);
		cust.setBirth(new Date());
		cust.setCreatedTime(new Date());
		cust.setEmail("8145970004@qq.com");
		cust.setLastName("qilizhi");
		Customer c = customerDao.save(cust);
		c.setLastName("DDDDDDDDDD");
		customerDao.saveAndFlush(c);
		List<Customer> cs = (List<Customer>) customerDao.findAll();
		System.out.println("testwwwwwww" + cs.toString());

		// String r = ecs.getsUser("ii1zfel35totirr");
		// System.out.println(r);

		/*
		 * ResultActions result = mockMvc
		 * .perform(MockMvcRequestBuilders.get("/v1/guide/list").param("page",
		 * "1").param("pageSize", "30")); MvcResult mrs =
		 * result.andDo(MockMvcResultHandlers.print()).andReturn();
		 * 
		 * int resultStr = mrs.getResponse().getStatus();
		 * Assert.assertEquals(200, resultStr);
		 */

	}

	@Test
	public void testFindByLastName() {

		List<Customer> customer = customerDao.findByLastName("qilizhi");
		System.out.println(customer.toString());
	}

	@Test
	public void testPage() {

		Pageable page = new PageRequest(1, 2);
		Page<Customer> customers = customerDao.findByLastName("qilizhi", page);
		System.out.println("��ҳ��" + customers.getSize());
		System.out.println("��ҳ��" + customers.getTotalPages());
		System.out.println("��ҳ��" + customers.getNumber());
		System.out.println("��ҳ��" + customers.getTotalElements());
		System.out.println("��ҳ��" + customers.getContent().toString());
	}

	@Test
	public void testNativeQuery() {
		Long t = customerDao.count();
		System.out.println(t);

	}

	@Test
	public void testQueryByExample() {
		Customer customer = new Customer();
		customer.setAge(20);
		Example<Customer> example = Example.of(customer, matching().withIgnorePaths("age").// ��������ֶ�

		withMatcher("lastname", ignoreCase()).withMatcher("email", ExampleMatcher.GenericPropertyMatchers.endsWith()));
		/*
		 * ExampleMatcher matcher =
		 * ExampleMatcher.matching().withMatcher("email",
		 * endsWith()).withMatcher("lastname", startsWith());
		 */

		// Example<Customer> example = Example.of(new Customer("Jon", "Snow"),
		// matcher);
		// Example<Customer> customerExample = Example.of(customer, matcher);
		List<Customer> list = (List<Customer>) customerDao.findAll(example);

		System.out.println(list.toString());

	}

	/**
	 * Ŀ��: ʵ�ִ���ѯ�����ķ�ҳ. id > 5 ������
	 * 
	 * ���� JpaSpecificationExecutor �� Page<T> findAll(Specification<T> spec,
	 * Pageable pageable); Specification: ��װ�� JPA Criteria ��ѯ�Ĳ�ѯ���� Pageable:
	 * ��װ�������ҳ����Ϣ: ���� pageNo, pageSize, Sort
	 */
	@Test
	public void testJpaSpecificationExecutor() {
		int pageNo = 0;
		int pageSize = 5;
		PageRequest pageable = new PageRequest(pageNo, pageSize);

		// ͨ��ʹ�� Specification �������ڲ���
		Specification<Customer> specification = new Specification<Customer>() {
			/**
			 * @param *root:
			 *            �����ѯ��ʵ����.
			 * @param query:
			 *            ���Դ��пɵ� Root ����, ����֪ JPA Criteria ��ѯҪ��ѯ��һ��ʵ����. ������
			 *            ����Ӳ�ѯ����, �����Խ�� EntityManager ����õ����ղ�ѯ�� TypedQuery ����.
			 * @param *cb:
			 *            CriteriaBuilder ����. ���ڴ��� Criteria ��ض���Ĺ���. ��Ȼ���Դ��л�ȡ��
			 *            Predicate ����
			 * @return: *Predicate ����, ����һ����ѯ����.
			 */
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("id");

				Predicate idPredicate = cb.gt(root.get("id").as(Integer.class), 2);
				Predicate emailpredicate = cb.equal(root.get("email").as(String.class), "814597006@qq.com");
				query.where(cb.and(idPredicate, emailpredicate));

				return query.getRestriction();
			}
		};

		Page<Customer> page = customerDao.findAll(specification, pageable);

		System.out.println("�ܼ�¼��: " + page.getTotalElements());
		System.out.println("��ǰ�ڼ�ҳ: " + (page.getNumber() + 1));
		System.out.println("��ҳ��: " + page.getTotalPages());
		System.out.println("��ǰҳ��� List: " + page.getContent());
		System.out.println("��ǰҳ��ļ�¼��: " + page.getNumberOfElements());
	}

	@Test
	public void testPagingAndSortingRespository() {
		// pageNo �� 0 ��ʼ.
		int pageNo = 6 - 1;
		int pageSize = 5;
		// Pageable �ӿ�ͨ��ʹ�õ��� PageRequest ʵ����. ���з�װ����Ҫ��ҳ����Ϣ
		// ������ص�. Sort ��װ���������Ϣ
		// Order �Ǿ��������ĳһ�����Խ��������ǽ���.
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Customer> page = customerDao.findAll(pageable);

		System.out.println("�ܼ�¼��: " + page.getTotalElements());
		System.out.println("��ǰ�ڼ�ҳ: " + (page.getNumber() + 1));
		System.out.println("��ҳ��: " + page.getTotalPages());
		System.out.println("��ǰҳ��� List: " + page.getContent());
		System.out.println("��ǰҳ��ļ�¼��: " + page.getNumberOfElements());
	}

	@Test
	public void testCache() {

		List<Customer> ll = customerDao.findAll();
		List<Customer> l2 = customerDao.findAll();
		logger.info("�����" + (ll == l2));
		Customer a = customerDao.getOne(1);
		Customer b = customerDao.getOne(1);
		logger.info("�����" + (a == b));

	}

	@Test
	public void QueryCacheTest() {
		// ��Ч��spring-data-jpaʵ�ֵĽӿڷ���
		// �������sql���
		customerDao.findAll();
		customerDao.findAll();
		System.out.println("================test 1 finish======================");
		// �Լ�ʵ�ֵ�dao�������Ա���ѯ����
		// ���һ��sql���
		customerDao.findAllCached();
		customerDao.findAllCached();
		System.out.println("================test 2 finish======================");
		// �Լ�ʵ�ֵ�dao�������Ա���ѯ����
		// ���һ��sql���
		customerDao.findCustomerByName("a");
		customerDao.findCustomerByName("a");
		System.out.println("================test 3 finish======================");
	}

	@Test
	public void TestQuery() {
		Authority a = new Authority();
		a.setId((long) 1);
		// List<Resource> l = resourceDao.getListByR((long) 1);
		List<Resource> ll = resourceDao.getListByEa((long) 1);
		// List<Customer>l=customerDao.getListByEa("ee");
	//	System.out.println(ll);
	}

}
