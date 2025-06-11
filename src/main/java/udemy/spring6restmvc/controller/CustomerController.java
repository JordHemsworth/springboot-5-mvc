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
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") UUID existingCustomerId, @RequestBody Customer customer) {

      customerService.updateCustById(existingCustomerId, customer);


        return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") UUID existingCustomerId, @RequestBody Customer customer) {
        customerService.deleteCustById(existingCustomerId, customer);

        return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<Customer> handleCustomerPost(@RequestBody Customer customer) {
        Customer savedNewCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/newCust" + savedNewCustomer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PatchMapping("{customerId}")
    public ResponseEntity<Customer> handleCustomerPatch(@PathVariable("customerId") UUID existingCustomerId, @RequestBody Customer customer) {

        customerService.patchCustById(existingCustomerId, customer);

        return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);
    }


}
