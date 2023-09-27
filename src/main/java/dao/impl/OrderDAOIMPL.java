package dao.impl;

import dao.OrderDAO;
import io.vavr.control.Either;
import model.Order;
import model.errors.OrderError;
import model.errors.OrderErrorEmptyList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

public class OrderDAOIMPL implements OrderDAO {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public Either<OrderError, List<Order>> getAll() {
        Either<OrderError, List<Order>> result;
        orders.add(new Order(222, LocalDateTime.of(1992, 9, 4,11,2,3), 1, 3));
        orders.add(new Order(223, LocalDateTime.of(1991, 5, 12,1,3,4), 2, 2));
        orders.add(new Order(224, LocalDateTime.of(1993, 2, 25,22,4,45), 3, 4));
        orders.add(new Order(225, LocalDateTime.of(1922, 12, 14,22,1,3), 4, 7));
        orders.add(new Order(226, LocalDateTime.of(1111, 1, 11,11,4,5), 5, 5));
        if(orders.isEmpty()) result=Either.left(new OrderErrorEmptyList());
        else result=Either.right(orders);
        return result ;
    }

    @Override
    public Either<OrderError, Order> get(Order order) {
        return null;
    }

    @Override
    public Either<OrderError, Integer> add(Order order) {
        if (!orders.add(order)) {
            return Either.right(0);
        }
        return Either.left(new OrderError(""));
    }

    @Override
    public Either<OrderError, Integer> update(Order order) {

        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).getOrder_Id() == order.getOrder_Id()) {
                orders.remove(orders.get(i));
                orders.add(order);
                return Either.right(0);
            }
        }

        return Either.left(new OrderError(""));
    }

    @Override
    public  Either<OrderError, Integer> delete(int id) {
        for (Order o : orders) {
            if (o.getOrder_Id() == id) {
                orders.remove(o);
                return Either.right(0);
            }

        }

        return Either.left(new OrderError(""));
    }

    @Override
    public boolean saveFile(String orderString, Path file) {
        OpenOption[] options = new OpenOption[2];
        options[0] = TRUNCATE_EXISTING;
        options[1] = WRITE;
        try (BufferedWriter writer = Files.newBufferedWriter(file, options)) {
            writer.newLine();
            writer.write(orderString, 0, orderString.length());
            return true;

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return false;
    }

    @Override
    public boolean readFile(Path file)throws IOException {
        BufferedReader reader=null;
        //as

        try{

            reader = Files.newBufferedReader(file);

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return true;
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }finally {
            if (reader != null) {
                reader.close();
            }
        }
        return false;
    }


}
