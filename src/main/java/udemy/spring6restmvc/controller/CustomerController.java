package udemy.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.spring6restmvc.model.Customer;
import udemy.spring6restmvc.service.CustomerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customers";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") UUID existingCustomerId, @RequestBody Customer customer) {

      customerService.updateCustById(existingCustomerId, customer);


        return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") UUID existingCustomerId) {
        customerService.deleteCustById(existingCustomerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Customer> handleCustomerPost(@RequestBody Customer customer) {
        Customer savedNewCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/newCust" + savedNewCustomer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> handleCustomerPatch(@PathVariable("customerId") UUID existingCustomerId, @RequestBody Customer customer) {

        customerService.patchCustById(existingCustomerId, customer);

        return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);
    }


}
