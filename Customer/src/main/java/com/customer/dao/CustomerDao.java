package com.customer.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.entity.Customer;

import jakarta.transaction.Transactional;
@Transactional
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

	@Query("SELECT u FROM Customer u WHERE u.id = :id")
	Customer getCustomerById(@Param("id") long id);

	Page<Customer> findByFirstName(String searchTerm, Pageable pageable);

	Page<Customer> findByCity(String searchTerm, Pageable pageable);

	Page<Customer> findByEmail(String searchTerm, Pageable pageable);

	Page<Customer> findByPhoneNumber(String searchTerm, Pageable pageable);

	Customer findByEmail(String email);

}
