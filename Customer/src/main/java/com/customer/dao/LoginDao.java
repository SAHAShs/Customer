package com.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.entity.Admin;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface LoginDao extends JpaRepository<Admin, Integer> {

	Admin findByUserName(String username);
}
