package services;

import dao.CustomerDAO;
import dao.impl.CustomerDAOIMPL;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.errors.CustomerError;

import java.util.List;

public class CustomerService {
    @Inject
    private CustomerDAO dao;

    public  Either<CustomerError, List<Customer>> getAll() {
        return dao.getAll();
    }

    public  Either<CustomerError, Customer> get(Customer customer) {
        return dao.get(customer);
    }

    public Either<CustomerError, Integer> add(Customer customer) {
        return dao.add(customer);
    }


    public Either<CustomerError, Integer> update(Customer customer) {
        return dao.update(customer);
    }

    public Either<CustomerError, Integer> delete(int id) {
        return dao.delete(id);
    }


}
