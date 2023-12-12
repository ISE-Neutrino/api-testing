package com.customers.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.customers.models.Customer;
import com.customers.services.CustomersService;
import com.customers.utils.ApiResponse;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/customers")
public class CustomersController {

    private CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }
   
    /**
     * List customers
     * 
     * @return customer collection
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getCustomers() {
        var result = customersService.getCustomers();
        return ApiResponse.Ok(result);
    }

    /**
     * Get customer
     * 
     * @param Id
     * @return customer
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable String id) {
        var result = customersService.getCustomer(id);
        return ApiResponse.Ok(result);
    }

     /**
     * Add new customer
     * 
     * @param customer model
     * @return created customerId
     */
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody Customer customer) {

        var customerId = customersService.addCustomer(customer);
        return ApiResponse.Ok(customerId);
    }

    /**
     * update customer
     * 
     * @param id customerId
     * @param customer model
     * @return update customerId
     */
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable String id,@RequestBody Customer customer) {
        var result = customersService.updateCustomer(id, customer);
        return ApiResponse.Ok(result);
    }


    /**
     * Delete customer
     * 
     * @param id customerId
     * @return deleted customerId
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable String id) {

        var result = customersService.deleteCustomer(id);
        return ApiResponse.Ok(result);
    }
}
