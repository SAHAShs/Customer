package com.customer.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.Customer;
/**
 * 
 * created to give dummy data to add sync feature
 */
@RestController
@RequestMapping("/dummy")
public class DummyDataController {
	@GetMapping("/")
    public List<Customer> getCustomers() {
        return Arrays.asList(
                new Customer("Emmeline", "Westberg", "11891 Reindahl Terrace", "10th Floor", "Battambang","Battambang", "ewestberg0@1688.com", "4229984615"),
                new Customer("Casey", "Beceril", "444 Forest Run Center", "Room 1278","Room 1278", "Tallahassee", "cbeceril1@timesonline.co.uk", "8506666093")
                // Add more customers as needed
        );
    }
}
