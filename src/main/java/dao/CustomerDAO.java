package dao;

import io.vavr.control.Either;
import model.Customer;
import model.errors.CustomerError;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface CustomerDAO {

    Either<CustomerError, List<Customer>> getAll();

    Either<CustomerError, Customer> get(Customer customer);

    Either<CustomerError, Integer> add(Customer customer);

    Either<CustomerError, Integer> update(Customer customer);

    Either<CustomerError, Integer> delete(int id);

    boolean saveFile(String customerString, Path file);


    boolean readFile(Path file)throws IOException;

}
