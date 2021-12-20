package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * @author-VipparlaSreeram
 */
public class CustomerService {

    private static Map<String, Customer> customerMap = new HashMap<String, Customer>();

    public static void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        customerMap.put(newCustomer.getEmail(), newCustomer);
    }

    public static Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }

}
