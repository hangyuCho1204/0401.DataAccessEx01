package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.dto.Customer;
import com.customer.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertCustomer(Model model) {

		Customer customer = new Customer();
		model.addAttribute("customer", customer);

		return "edit";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertCustomerPost(@ModelAttribute Customer customer, Model model) {

		int result;

		result = customerService.saveCustomer(customer);

		return "redirect:list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	// 여기서 이름을 주지 않으면 클래스명+List가 붙는다. 여기서는 customerList
	public List<Customer> listCustomer(Model model) {
		System.out.println("list");
		List<Customer> list = customerService.getCustomers();
		
		
		model.addAttribute("customers", list);
		
		return list;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateCustomer(@RequestParam int id, Model model) {
		Customer customer = customerService.getByIdCustomer(id);

		model.addAttribute("customer", customer);

		return "update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCustomerExec(@ModelAttribute Customer customer) {
		
		customerService.modifyCustomer(customer);

		return "redirect:list";
		// redirect는 요청을 다시 해주는 명령.
		// redirect에 적혀진 주소로 가기 때문에 해당 메소드(위 경우는 /list)를 다시 실행해준다고 보면 됨
		// 아예 새로 다시 요청이기 때문에 풀 주소를 다 써줘야 함.
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam String name) {

		customerService.removeCustomer(name);

		return "redirect:list";
	}
}
