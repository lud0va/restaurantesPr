package ui.pantallas.orders;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import services.OrderService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.ConstantsScreens;

import java.time.LocalDateTime;

public class OrderModifyController extends BaseScreenController {
    private final OrderService sv;
    @FXML
    private Label textMod;
    @FXML
    private TableView<Order> tableOrd;
    @FXML
    private TableColumn<Order, String> idTable;
    @FXML
    private TableColumn<Order, String> dateTable;
    @FXML
    private TableColumn<Order, String> customerTable;
    @FXML
    private TableColumn<Order, String> tabTable;
    @FXML
    private TextField dateMod;
    @FXML
    private TextField custMod;
    @FXML
    private TextField tableMod;

    @Inject
    private OrderModifyController(OrderService sv) {

        this.sv = sv;
    }

    public void clickButton(ActionEvent actionEvent) {
            Order idord=tableOrd.getSelectionModel().getSelectedItem();
            Order o = new Order(idord.getOrder_Id(), LocalDateTime.parse(dateMod.getText()),Integer.parseInt(custMod.getText()),Integer.parseInt(tableMod.getText()));
            sv.update(o);
            textMod.setText(ConstantsScreens.Order_Upd);



}

    @Override
    public void principalCargado() {
        idTable.setCellValueFactory(new PropertyValueFactory<>("order_Id"));
        dateTable.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        customerTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tabTable.setCellValueFactory(new PropertyValueFactory<>("table_Number"));

        sv.getAll()
                .peek(customers -> tableOrd.getItems().addAll(customers))
                .peekLeft(integer -> {
                });

    }

    public void clickTable(MouseEvent mouseEvent) {
        Order o = tableOrd.getSelectionModel().getSelectedItem();
        dateMod.setText(o.getOrder_date().toString());
        custMod.setText(String.valueOf(o.getCustomerId()));
        tableMod.setText(String.valueOf(o.getTable_Number()));

    }
}
