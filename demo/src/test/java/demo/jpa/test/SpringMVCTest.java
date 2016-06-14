package demo.jpa.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.qlz.dao.CustomerDao;
import com.qlz.entities.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp,src/main/resources")
@ContextHierarchy({
		@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:applicationContext.xml" }) })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringMVCTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private CustomerDao customerDao;

	// Ê†πÊçÆÂÖ∑‰ΩìË¶ÅÊµãËØïÁöÑcontroller ÊµãËØï
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
	// // ËøîÂõûÁä∂Ê?Á†?
	// int resultStr = mrs.getResponse().getStatus();
	//
	// // Â¶ÇÊûúËøîÂõûÁä∂Ê?Áõ∏Á≠â Â∞±È?Ëø?
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
		customerDao.save(cust);
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
		System.out.println("∑÷“≥£∫"+customers.getSize());
		System.out.println("∑÷“≥£∫"+customers.getTotalPages());
		System.out.println("∑÷“≥£∫"+customers.getNumber());
		System.out.println("∑÷“≥£∫"+customers.getTotalElements());
		System.out.println("∑÷“≥£∫"+customers.getContent().toString());
	}

}
