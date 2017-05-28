package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.dto.Customer;
import com.customer.repository.CustomerRepository;

// ���� �ڵ���ĵ
@Service
public class CustomerService {

	// �ڵ���ĵ �� repository�� ���� ����.
	@Autowired
	private CustomerRepository customerRepository;

	// Customer��ü�� ���� �� ��ü�� DB�� �����ϴ� ���� ��
	public int saveCustomer(Customer customer) {

		// �����Ϸ��� ȸ���� ��ȿ�� �˻� ���� �����۾� ������ ���� �κ�

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
