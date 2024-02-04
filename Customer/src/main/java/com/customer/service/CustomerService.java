package com.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.customer.dao.CustomerDao;
import com.customer.entity.Customer;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CustomerService {
	CustomerDao addCustomerDao;

	@Autowired
	public CustomerService(CustomerDao addCustomerDao) {
		this.addCustomerDao = addCustomerDao;
	}
	/**
	 * saves user entity
	 * 
	 * @param customer
	 * @return customer
	 */
	public Customer saveUser(Customer customer) {
		return addCustomerDao.save(customer);
	}

	/**
	 * finds and returns customer by id
	 * 
	 * @param id
	 * @return customer 
	 */
	public Customer getCustomerById(long id) {
		return addCustomerDao.findById(id).orElse(null);
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedCustomer
	 * @return
	 */
	public Customer updateCustomer(long id, Customer updatedCustomer) {
		
		Customer exCustomer = addCustomerDao.getCustomerById(id);
		System.out.println(updatedCustomer.toString());
		if (exCustomer != null) {
			System.out.println(exCustomer.toString());
			exCustomer.setFirstName(updatedCustomer.getFirstName());
			exCustomer.setLastName(updatedCustomer.getLastName());
			exCustomer.setAddress(updatedCustomer.getAddress());
			exCustomer.setCity(updatedCustomer.getCity());
			exCustomer.setEmail(updatedCustomer.getEmail());
			exCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
			exCustomer.setState(updatedCustomer.getState());
			exCustomer.setStreet(updatedCustomer.getStreet());
			Customer save = addCustomerDao.save(exCustomer);
			System.out.println("save" + save);
			return save;
		} else {
			return null;
		}

	}
	
	/**
	 * provides service for deleting xustomer on id
	 * 
	 * @param id
	 * @return boolean status
	 */
	public boolean deleteCustomer(long id) {
		boolean dataDeleted=false;
		Customer exCustomer = addCustomerDao.getCustomerById(id);
		if (exCustomer != null) {
			addCustomerDao.deleteById(id);
			dataDeleted=true;
		} 
		return dataDeleted;
	}

	/**
	 *pagination and sorting is achieved
	 *custom search 
	 * 
	 * @param searchCriteria
	 * @param searchTerm
	 * @param pageNumber
	 * @param records
	 * @param sortField
	 * @param sortOrder
	 * @return
	 */
	public Page<Customer> searchCustomers(String searchCriteria, String searchTerm, int pageNumber, int records,
			String sortField, String sortOrder) {
		System.out.println(pageNumber);
		Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
		Pageable pageable = PageRequest.of(pageNumber, records, sort);

		switch (searchCriteria) {
		case "firstName":
			return addCustomerDao.findByFirstName(searchTerm, pageable);
		case "city":
			return addCustomerDao.findByCity(searchTerm, pageable);
		case "email":
			return addCustomerDao.findByEmail(searchTerm, pageable);
		case "phoneNumber":
			return addCustomerDao.findByPhoneNumber(searchTerm, pageable);
		default:
			throw new IllegalArgumentException("Invalid search criteria: " + searchCriteria);
		}
	}

	/**
	 * finds the user if present updates them else save them
	 * 
	 * @param remoteCustomer
	 * @return boolean status of sync
	 */
	public boolean syncCustomer(Customer remoteCustomer) {
		try {
			Customer existingCustomer = addCustomerDao.findByEmail(remoteCustomer.getEmail());

			if (existingCustomer != null) {
				existingCustomer.setFirstName(remoteCustomer.getFirstName());
				existingCustomer.setLastName(remoteCustomer.getLastName());
				existingCustomer.setStreet(remoteCustomer.getStreet());
				existingCustomer.setAddress(remoteCustomer.getAddress());
				existingCustomer.setCity(remoteCustomer.getCity());
				existingCustomer.setState(remoteCustomer.getState());
				existingCustomer.setEmail(remoteCustomer.getEmail());
				existingCustomer.setPhoneNumber(remoteCustomer.getPhoneNumber());
				addCustomerDao.save(existingCustomer);
//				updateCustomer(remoteCustomer.getId(), existingCustomer);
			} else {
				addCustomerDao.save(remoteCustomer);
			}
			return true; // Synchronization successful
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Synchronization failed
		}
	}

}
