package customer_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customer_management.model.Customer;
import customer_management.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository repository;
	
	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}
	
	public List<Customer> getAllCustomers(){
		return repository.findAll();
	}
	
	public Customer getCustomerById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Customer updateCustomer(Long id, Customer customer) {
		Customer existing = getCustomerById(id);
		existing.setName(customer.getName());
		existing.setEmail(customer.getEmail());
		existing.setPhone(customer.getPhone());
		existing.setCity(customer.getCity());
		
		return repository.save(existing);
	}
	
	public void deleteCustomer(Long id) {
		Customer customer=getCustomerById(id);
		repository.delete(customer);
	}
	
	public List<Customer> searchCustomer(String name){
		return repository.findByNameContainingIgnoreCase(name);
	}
	
}
