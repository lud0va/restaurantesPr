package services;

import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.errors.OrderError;

import java.util.List;

public class OrderService {

    @Inject
    private OrderDAO od;

    public  Either<OrderError, List<Order>> getAll() {
        return od.getAll();
    }

    public Either<OrderError, Order> get(Order order) {
        return od.get(order);
    }

    public Either<OrderError, Integer> add(Order order) {
        return od.add(order);
    }

    public Either<OrderError, Integer> update(Order order) {
        return od.update(order);
    }

    public Either<OrderError, Integer> delete(int id) {
        return od.delete(id);
    }
}
