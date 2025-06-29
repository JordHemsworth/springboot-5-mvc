package udemy.spring6restmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import udemy.spring6restmvc.model.Customer;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {

        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Alice Johnson")
                .version(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Bob Martinez")
                .version(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Clara Nguyen")
                .version(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {

        log.debug("Get Beer ID - in service. Id: " + id.toString());

        return Optional.of(customerMap.get(id));
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {

        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();

        customerMap.put(customer.getId(), savedCustomer);

        return savedCustomer;

    }

    @Override
    public void updateCustById(UUID existingCustomerId, Customer customer) {
        Customer existingCustomer = customerMap.get(existingCustomerId);

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setLastModifiedDate(LocalDate.now());
    }

    @Override
    public void deleteCustById(UUID existingCustomerId) {
        customerMap.remove(existingCustomerId);
    }

    @Override
    public void patchCustById(UUID existingCustomerId, Customer customer) {
        Customer existingCustomer = customerMap.get(existingCustomerId);

        if (StringUtils.hasLength(customer.getCustomerName())) {
            existingCustomer.setCustomerName(customer.getCustomerName());
        }

    }
}
