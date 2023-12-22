package com.customers.services;

import com.customers.models.Customer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomersService {

    static ConcurrentHashMap<String, Customer> customers = new  ConcurrentHashMap<String, Customer>();

    public CustomersService() {
        seed();
    }

    /**
     * List customers
     * @return
     */
    public Collection<Customer> getCustomers() {
        return customers.values();
    }
    
    /**
     * Get customer
     * @param id
     * @return
     */
    public Customer getCustomer(String id) {
        return customers.get(id);
    }
    
    /**
     * Add new customer
     * @param customer
     * @return
     */
    public String addCustomer(Customer customer) {
        customer.id = UUID.randomUUID().toString();
        customers.put(customer.id, customer);
        return customer.id;
    }
    
    /**
     * Update customer
     * @param id
     * @param customer
     * @return
     */
    public String updateCustomer(String id, Customer customer) {
        customer.id = id;
        customers.replace(id, customer);
        return id;
    }
    
    /**
     * Delete customer
     * @param id
     * @return
     */
    public String deleteCustomer(String id) {
        return customers.remove(id).id;
    }

    /**
     * Seed data
     */
    private void seed() {
       Customer customer = new Customer();
        customer.id = "c9cd67b8-7738-4736-b8c6-f35bb0154d09";
        customer.name = "Customer 1";
        customer.email = "customer@email.com";
        customers.put(customer.id, customer);
    }
}
