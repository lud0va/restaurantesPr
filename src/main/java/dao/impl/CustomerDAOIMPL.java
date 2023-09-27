package dao.impl;

import dao.CustomerDAO;
import io.vavr.control.Either;
import model.Customer;
import model.errors.CustomerError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOIMPL implements CustomerDAO {
    private  List<Customer> customers = new ArrayList<>();

    @Override
    public Either<CustomerError, List<Customer>> getAll() {

        customers.add(new Customer(1, "Snake", "Gomez", "Gomezmoreno@gmail.com", "141-141-444", LocalDate.of(1992, 9, 4)));
        customers.add(new Customer(2, "John", "Smith", " john.smith@example.com", "123-456-7890", LocalDate.of(1992, 3, 1)));
        customers.add(new Customer(3, "Sarah", "Johnson", "sarah.johnson@example.com", "987-654-3210", LocalDate.of(1992, 4, 13)));
        customers.add(new Customer(4, "Michael", "Davis", "michael.davis@example.com", "555-123-4567", LocalDate.of(1992, 6, 17)));
        customers.add(new Customer(5, "Laura", "Brown", "laura.brown@example.com", "222-333-4444", LocalDate.of(1992, 2, 11)));
        return Either.right(customers);
    }

    @Override
    public Either<CustomerError, Customer> get(Customer customer) {
        return null;
    }

    @Override
    public Either<CustomerError, Integer> add(Customer customer) {
        customers.add(customer);
        return Either.left(new CustomerError(1,""));
    }

    @Override
    public Either<CustomerError, Integer> update(Customer customer) {

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getIdCustomer() == customer.getIdCustomer()) {
                customers.remove(customers.get(i));
                customers.add(customer);
                return Either.right(0);
            }

        }
        return Either.left(new CustomerError(1,""));
    }

    @Override
    public Either<CustomerError, Integer> delete(int id) {

        for (Customer c : customers) {
            if (c.getIdCustomer() == id) {
                customers.remove(c);

                return Either.right(0);
            }

        }

        return Either.left(new CustomerError(2,""));
    }
}
