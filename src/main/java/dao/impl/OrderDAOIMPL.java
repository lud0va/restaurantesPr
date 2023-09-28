package dao.impl;

import config.Configuration;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.errors.OrderError;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;


public class OrderDAOIMPL implements OrderDAO {
    private final List<Order> orders = new ArrayList<>();
    Path file= Paths.get(Configuration.getInstance().getProperty("pathFileOrd"));



    @Override
    public Either<OrderError, List<Order>> getAll() {
        Either<OrderError, List<Order>> result;


        try {
            result = Either.right(readFile(file, orders));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Either<OrderError, Order> get(Order order) {
        return null;
    }

    @Override
    public Either<OrderError, Integer> add(Order order) {
        orders.add(order);
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
    public Either<OrderError, Integer> delete(int id) {
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
    public List<Order> readFile(Path file, List<Order> orders) throws IOException {
        BufferedReader reader = null;
        //as

        try {

            reader = Files.newBufferedReader(file);

            String line = null;
            String[] s;
            while ((line = reader.readLine()) != null) {
                s = line.split(";");
                orders.add(new Order(Integer.parseInt(s[1]), LocalDateTime.parse(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4])));
                System.out.println(line);
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return orders;
    }


}
