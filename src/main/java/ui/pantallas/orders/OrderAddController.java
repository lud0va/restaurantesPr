package ui.pantallas.orders;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Order;
import services.OrderService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.ConstantsScreens;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAddController extends BaseScreenController {
    private final OrderService sc;
    @FXML
    private TextField customerTable;
    @FXML
    private TextField tabTable;
    @FXML
    private Label textAddOrd;
    @FXML
    private TextField dateAdd;

    @Inject
    public OrderAddController(OrderService sc) {

        this.sc = sc;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();
    }

    public void clickButton(ActionEvent actionEvent) {

        if (customerTable.getText().isEmpty() || tabTable.getText().isEmpty() || dateAdd.getText().isEmpty()) {
        } else {

            List<Order> orders = new ArrayList<>();
            sc.getAll()
                    .peek(order -> orders.addAll(order))
                    .peekLeft(integer -> {
                    });

            sc.add(new Order(orders.size()+1, LocalDateTime.parse(dateAdd.getText()), Integer.parseInt(customerTable.getText()), Integer.parseInt(tabTable.getText())));


            textAddOrd.setText(ConstantsScreens.Order_Add);
        }


    }


}
