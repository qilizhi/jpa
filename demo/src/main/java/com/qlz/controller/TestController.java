package com.qlz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlz.dao.CustomerDao;
import com.qlz.entities.Customer;
import com.qlz.service.CustomerService;

@RequestMapping("/test")
@Controller
public class TestController {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerService customerService;
	@RequestMapping
	public String test() {

		System.out.println("testController");
		return "/helloword/test";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Map<String, Object> jsonTest() {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("testControll", "OK");
		System.out.println("testController ok");
		return maps;
	}
	
	@RequestMapping("/customers/{lastName}")
	@ResponseBody
	public List<Customer> getCustomers(@PathVariable ("lastName")String lastName,Integer page,Integer size){
		Pageable pages =new PageRequest(page, size,new Sort(Direction.DESC, "id","lastName"));
		Page<Customer> customers=customerDao.findByLastName(lastName, pages);
		return customers.getContent();
	}
	
	@RequestMapping("/customers")
	@ResponseBody
	public List<Customer> listCustomer(@PageableDefault(page=0,size=2,sort="id",direction=Direction.ASC)Pageable page){
		Page<Customer> customers=customerDao.findAll(page);
		return customers.getContent();
	}
	
	@RequestMapping("/customer/all")
	@ResponseBody
	public List<Customer> listAll(){
		
		List<Customer> customers=(List<Customer>) customerDao.findAll();
		return customers;
		
	}
	
	@RequestMapping("/customer/email")
	@ResponseBody
	public List<Customer> getCustomerByEmail(String email){
		List<Customer> cs=customerDao.getListByEmail(email);
		return cs;
	}
	@RequestMapping("/customerOne/email")
	@ResponseBody
	public Customer getCustomerOneByEmail(@RequestParam("email")String email){
		Customer c=customerDao.findOneByEmail(email);
		return c;
	}
	@RequestMapping("/customer")
	@ResponseBody
	public List<Customer> getCustomerOneByEmailAndlastName(@RequestParam("email")String email,String lastName){
		List<Customer> c=customerDao.getListByEmailAndLastName(email, lastName);
		return c;
	}

	@RequestMapping("/customer/{id}/{lastName}")
	@ResponseBody
	public String  updateLastNameById(@PathVariable("id")Integer id,@PathVariable("lastName")String lastName){
		customerDao.updateByLastNameById(id, lastName);
		return "OK";
	}
	
	/*@RequestMapping("/customer/{id}/{lastName}")
	@ResponseBody
	public String  updateLastNameById(@PathVariable("id")Integer id,@PathVariable("lastName")String lastName){
		customerService.updateCustomer(lastName, id);
		return "OK";
	}*/

	
	/*@RequestMapping("/customer")
	@ResponseBody
	public List<Customer> listByExample(Customer customer){
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email",endsWith("qq.com"))
			    .withMatcher("lastname", startsWith().ignoreCase());

			Example<Person> example = Example.of(new Person("Jon", "Snow"), matcher); 
		Example<Customer> customerExample=Example.of(customer,);
		return (List<Customer>) customerDao.findAll(customerExample);
		
	}*/
}
