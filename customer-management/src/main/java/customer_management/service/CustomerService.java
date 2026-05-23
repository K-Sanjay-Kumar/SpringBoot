package customer_management.service;

import java.util.List;

import customer_management.model.Customer;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Long id);
	Customer updateCustomer(Long id, Customer customer);
	void deleteCustomer(Long id);
	List<Customer> searchCustomer(String name);
}
