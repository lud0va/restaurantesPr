package ui.pantallas.orders;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import services.OrderService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.ConstantsScreens;

public class OrderDeleteController extends BaseScreenController {

    private final OrderService sv;
    public Label textDel;

    @FXML
    private TableView<Order> tableDel;
    @FXML
    private TableColumn<Order, String> idTable;
    @FXML
    private TableColumn<Order, String> orderTable;
    @FXML
    private TableColumn<Order, String> customerTable;
    @FXML
    private TableColumn<Order, String> numberTable;

    @Inject
    public OrderDeleteController(OrderService sv) {
        this.sv = sv;
    }

    public void clickButton(ActionEvent actionEvent) {
        Order o = tableDel.getSelectionModel().getSelectedItem();
        sv.delete(o.getOrder_Id());
        textDel.setText(ConstantsScreens.Order_Del);


    }

    @Override
    public void principalCargado() {
        idTable.setCellValueFactory(new PropertyValueFactory<>("order_Id"));
        orderTable.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        customerTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        numberTable.setCellValueFactory(new PropertyValueFactory<>("table_Number"));

        sv.getAll()
                .peek(customers -> tableDel.getItems().addAll(customers))
                .peekLeft(integer -> {
                });


    }
}
