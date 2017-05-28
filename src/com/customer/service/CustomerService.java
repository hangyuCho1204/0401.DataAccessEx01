package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.dto.Customer;
import com.customer.repository.CustomerRepository;

// 역시 자동스캔
@Service
public class CustomerService {

	// 자동스캔 된 repository가 여기 붙음.
	@Autowired
	private CustomerRepository customerRepository;

	// Customer객체가 오면 그 객체를 DB에 적용하는 일을 함
	public int saveCustomer(Customer customer) {

		// 가입하려는 회원의 유효성 검사 등의 사전작업 로직이 들어가는 부분

		return customerRepository.insert(customer);
	}

	public List<Customer> getCustomers() {

		return customerRepository.selectAll();
	}

	public Customer getByIdCustomer(int id) {

		return customerRepository.selectById(id);
	}

	public int modifyCustomer(Customer customer) {

		return customerRepository.update(customer);
	}
	
	public int removeCustomer(String id) {

		return customerRepository.delete(id);
	}
}
