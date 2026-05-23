package customer_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import customer_management.model.Customer;
import customer_management.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/home")
	public String home(HttpServletRequest http) {
		return "Home page"+http.getSession().getId();
	}
	
	@PostMapping("/api/customers/addCustomers")
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	
	@GetMapping("/api/customers/getAllCustomers")
	public List<Customer> getAllCustomers(){
		return service.getAllCustomers();
	}
	
	@GetMapping("/api/customers/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return service.getCustomerById(id);
	}
	
	@PutMapping("/api/customers/{id}")
	public Customer updateCustomerById(@PathVariable Long id, @RequestBody Customer cutomer) {
		return service.updateCustomer(id, cutomer);
	}
	
	@DeleteMapping("/api/customers/{id}")
	public String deleteCustomerById(@PathVariable Long id) {
		service.deleteCustomer(id);
		return "Customer deleted successfully";
	}
	
	@GetMapping("/api/customers/search")
	public List<Customer> searchCustomer(@RequestParam String name){
		return service.searchCustomer(name);
	}
	
}
