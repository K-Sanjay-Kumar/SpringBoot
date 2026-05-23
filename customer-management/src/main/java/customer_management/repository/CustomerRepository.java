package customer_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import customer_management.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByNameContainingIgnoreCase(String name);
}
