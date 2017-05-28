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
	// ���⼭ �̸��� ���� ������ Ŭ������+List�� �ٴ´�. ���⼭�� customerList
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
		// redirect�� ��û�� �ٽ� ���ִ� ���.
		// redirect�� ������ �ּҷ� ���� ������ �ش� �޼ҵ�(�� ���� /list)�� �ٽ� �������شٰ� ���� ��
		// �ƿ� ���� �ٽ� ��û�̱� ������ Ǯ �ּҸ� �� ����� ��.
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam String name) {

		customerService.removeCustomer(name);

		return "redirect:list";
	}
}
