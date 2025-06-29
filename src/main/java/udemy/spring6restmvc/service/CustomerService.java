package udemy.spring6restmvc.service;

import org.springframework.stereotype.Service;
import udemy.spring6restmvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(UUID id);

    Customer saveNewCustomer(Customer customer);

    void updateCustById(UUID existingCustomerId, Customer customer);

    void deleteCustById(UUID existingCustomerId);

    void patchCustById(UUID existingCustomerId, Customer customer);
}
