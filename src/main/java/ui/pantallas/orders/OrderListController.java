package ui.pantallas.orders;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import services.OrderService;
import ui.pantallas.common.BaseScreenController;

public class OrderListController extends BaseScreenController {
    private final OrderService sv;
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

}
