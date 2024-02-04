package com.customer.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.entity.Customer;
import com.customer.service.CustomerService;

@RestController
public class CustomerController {
	@Value("${remote.api.url}")
	private String remoteApiUrl;

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * Endpoint for syncing customers from a remote API.
	 *
	 * @return ResponseEntity indicating the success or failure of the
	 *         synchronization.
	 */
	@GetMapping("/sync")
	public ResponseEntity<String> syncCustomers() {
		ResponseEntity<Customer[]> response = new RestTemplate().getForEntity(remoteApiUrl, Customer[].class);

		Customer[] remoteCustomers = response.getBody();

		if (remoteCustomers != null) {
			for (Customer remoteCustomer : remoteCustomers) {
				customerService.syncCustomer(remoteCustomer);
			}
			return ResponseEntity.ok("Sync successful");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error syncing customers");
		}
	}

	/**
	 * Handles the HTTP GET request to retrieve customer information by ID.
	 *
	 * @param id The ID of the customer to retrieve.
	 * @return ResponseEntity containing the customer information if found, else
	 *         NOT_FOUND.
	 */
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 *
	 * creates a new customer
	 * 
	 * @param newCustomer
	 * @return ResponseEntity containing the new customer information
	 */
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
		Customer createdCustomer = customerService.saveUser(newCustomer);
		if (createdCustomer != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	/**
	 * update the customer entity on id
	 * 
	 * @param id
	 * @param updatedCustomer
	 * @return ResponseEntity containing the updated customer information, else
	 *         NOT_FOUND.
	 */
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer updatedCustomer) {
		Customer updated = customerService.updateCustomer(id, updatedCustomer);
		if (updated != null) {
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * deleting customer based on id
	 * 
	 * @param id
	 * @return ResponseEntity conatining boolean determining delete status
	 */
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Boolean> deleteCustomer(@PathVariable long id) {
		return ResponseEntity.ok(customerService.deleteCustomer(id));
	}

	/**
	 * return the customer with pagination and sorting
	 * 
	 * @param searchCriteria
	 * @param searchTerm
	 * @param pageNumber
	 * @param records
	 * @param sortField
	 * @param sortOrder
	 * @return customer
	 */
	@GetMapping("/search")
	public ResponseEntity<Page<Customer>> searchCustomers(@RequestParam(required = false) String searchCriteria,
			@RequestParam(required = false) String searchTerm, @RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int records,
			@RequestParam(required = false, defaultValue = "id") String sortField,
			@RequestParam(required = false, defaultValue = "ASC") String sortOrder) {
		Page<Customer> page = customerService.searchCustomers(searchCriteria, searchTerm, pageNumber, records,
				sortField, sortOrder.toUpperCase());
		return ResponseEntity.ok(page);
	}
}
