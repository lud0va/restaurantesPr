package ui.pantallas.orders;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Order;
import services.OrderService;
import ui.pantallas.common.BaseScreenController;

import java.time.LocalDateTime;
import java.util.List;

public class OrderListController extends BaseScreenController {
    private final OrderService sv;


    @FXML
    private TextField customerFilterText;
    @FXML
    private TextField dateFilterText;
    @FXML
    private MenuItem filterDate;

    @FXML
    private MenuItem filterCustomer;
    @FXML
    private TableView<Order> tableOrder;

    @FXML
    private TableColumn<Order, String> idTable;
    @FXML
    private TableColumn<Order, String> dateTable;
    @FXML
    private TableColumn<Order, String> customerIdTable;
    @FXML
    private TableColumn<Order, String> idtabTable;

    @Inject
    public OrderListController(OrderService sv) {

        this.sv = sv;
    }

    @Override
    public void principalCargado() {
        idTable.setCellValueFactory(new PropertyValueFactory<>("order_Id"));
        dateTable.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        customerIdTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        idtabTable.setCellValueFactory(new PropertyValueFactory<>("table_Number"));

        sv.getAll()
                .peek(customers -> tableOrder.getItems().addAll(customers))
                .peekLeft(integer -> {
                });


    }

    public void filterByCust(){
        tableOrder.getItems().clear();
        List<Order> order=sv.getAll().get();
        order= order.stream().filter(ord ->ord.getCustomerId()==Integer.parseInt(customerFilterText.getText())).toList();
        tableOrder.getItems().addAll(order);



    }

    public void filterByDate(){
        tableOrder.getItems().clear();
        List<Order> order=sv.getAll().get();
        order= order.stream().filter(ord -> ord.getOrder_date().equals(LocalDateTime.parse(dateFilterText.getText()))).toList();
        tableOrder.getItems().addAll(order);
    }

    public void menuFilterClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "filterDate":
                filterByDate();
                break;
            case "filterCustomer":
                filterByCust();
                break;


        }

    }
}
