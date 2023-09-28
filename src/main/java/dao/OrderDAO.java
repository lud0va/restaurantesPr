package dao;

import io.vavr.control.Either;

import model.Order;
import model.errors.OrderError;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface OrderDAO {
    Either<OrderError, List<Order>> getAll();

    Either<OrderError, Order> get(Order order);

    Either<OrderError, Integer> add(Order order);

    Either<OrderError, Integer> update(Order order);

    Either<OrderError, Integer> delete(int id);

    boolean saveFile(String orderString, Path file);


    List<Order>  readFile(Path file,List<Order> orders)throws IOException;


}
