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
/** 声明事务回滚，要不测试一个方法数据就没有了岂不很杯具，注意：插入数据时可注掉，不让事务回滚 **/
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

	// 鏍规嵁鍏蜂綋瑕佹祴璇曠殑controller 娴嬭瘯
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
	// // 杩斿洖鐘舵?鐮?
	// int resultStr = mrs.getResponse().getStatus();
	//
	// // 濡傛灉杩斿洖鐘舵?鐩哥瓑 灏遍?杩?
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
		System.out.println("分页：" + customers.getSize());
		System.out.println("分页：" + customers.getTotalPages());
		System.out.println("分页：" + customers.getNumber());
		System.out.println("分页：" + customers.getTotalElements());
		System.out.println("分页：" + customers.getContent().toString());
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
		Example<Customer> example = Example.of(customer, matching().withIgnorePaths("age").// 忽略这个字段

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
	 * 目标: 实现带查询条件的分页. id > 5 的条件
	 * 
	 * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec,
	 * Pageable pageable); Specification: 封装了 JPA Criteria 查询的查询条件 Pageable:
	 * 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
	 */
	@Test
	public void testJpaSpecificationExecutor() {
		int pageNo = 0;
		int pageSize = 5;
		PageRequest pageable = new PageRequest(pageNo, pageSize);

		// 通常使用 Specification 的匿名内部类
		Specification<Customer> specification = new Specification<Customer>() {
			/**
			 * @param *root:
			 *            代表查询的实体类.
			 * @param query:
			 *            可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 *            来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb:
			 *            CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到
			 *            Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
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

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前第几页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面的记录数: " + page.getNumberOfElements());
	}

	@Test
	public void testPagingAndSortingRespository() {
		// pageNo 从 0 开始.
		int pageNo = 6 - 1;
		int pageSize = 5;
		// Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
		// 排序相关的. Sort 封装了排序的信息
		// Order 是具体针对于某一个属性进行升序还是降序.
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Customer> page = customerDao.findAll(pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前第几页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面的记录数: " + page.getNumberOfElements());
	}

	@Test
	public void testCache() {

		List<Customer> ll = customerDao.findAll();
		List<Customer> l2 = customerDao.findAll();
		logger.info("结果：" + (ll == l2));
		Customer a = customerDao.getOne(1);
		Customer b = customerDao.getOne(1);
		logger.info("结果：" + (a == b));

	}

	@Test
	public void QueryCacheTest() {
		// 无效的spring-data-jpa实现的接口方法
		// 输出两条sql语句
		customerDao.findAll();
		customerDao.findAll();
		System.out.println("================test 1 finish======================");
		// 自己实现的dao方法可以被查询缓存
		// 输出一条sql语句
		customerDao.findAllCached();
		customerDao.findAllCached();
		System.out.println("================test 2 finish======================");
		// 自己实现的dao方法可以被查询缓存
		// 输出一条sql语句
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
