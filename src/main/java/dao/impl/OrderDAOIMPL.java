package dao.impl;

import config.Configuration;
import dao.OrderDAO;
import io.vavr.control.Either;
import model.Order;
import model.errors.OrderError;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;


public class OrderDAOIMPL implements OrderDAO {
    private List<Order> orders = new ArrayList<>();
    Path file = Paths.get(Configuration.getInstance().getProperty("pathFileOrd"));

    public OrderDAOIMPL() {
        try {
            orders = readFile(file, orders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Either<OrderError, List<Order>> getAll() {
        Either<OrderError, List<Order>> result;

        result = Either.right(orders);
        return result;
    }

    @Override
    public Either<OrderError, Order> get(Order order) {
        return null;
    }

    @Override
    public Either<OrderError, Integer> add(Order order) {
        orders.add(order);
        orders.forEach(order1 -> {
            saveFile();
        });

        return Either.left(new OrderError(""));
    }

    @Override
    public Either<OrderError, Integer> update(Order order) {

        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).getOrder_Id() == order.getOrder_Id()) {
                orders.remove(orders.get(i));
                orders.add(order);
                saveFile();
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

                saveFile();

                return Either.right(0);
            }

        }

        return Either.left(new OrderError(""));
    }


    public void saveFile() {
        List<String> orderLines = new ArrayList<>();
        orders.forEach(order -> orderLines.add(order.toStringTextFile()));
        /*OpenOption[] options = new OpenOption[2];
        options[0] = TRUNCATE_EXISTING;
        options[1] = WRITE;*/


        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String line : orderLines) {
                writer.write(line);
                writer.newLine();

            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }


    }


    public List<Order> readFile(Path file, List<Order> orders) throws IOException {
        BufferedReader reader = null;
        //as

        try {

            reader = Files.newBufferedReader(file);

            String line = null;
            String[] s;

            while ((line = reader.readLine()) != null) {

                orders.add(new Order(line));

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
