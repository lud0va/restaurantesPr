package services;

import dao.CustomerDAO;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.Order;
import model.errors.CustomerError;
import model.errors.OrderError;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    @Inject
    private OrderDAO od;
    @Inject

    private CustomerDAO co;

    public Either<OrderError, List<Order>> getAll() {
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



    public Either<OrderError, List<Order>> filterByCust(String customer) {

        final int[] id = new int[1];
        co.getAll().peek(customers -> customers.forEach(customer1 -> {
            if (customer1.getName().equals(customer)){
                id[0] =customer1.getIdCustomer();
            }

        }));
        return getAll().peek(orders -> orders.stream().filter(order -> order.getCustomerId() ==id[0]));

    }


}
