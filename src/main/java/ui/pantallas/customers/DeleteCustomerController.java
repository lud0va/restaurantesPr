package ui.pantallas.customers;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import services.CustomerService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.ConstantsScreens;

public class DeleteCustomerController extends BaseScreenController {
    private final CustomerService sc;
    @FXML
    private Label textDel;
    @FXML
    private TableView<Customer> listCustDel;
    @FXML
    private TableColumn<Customer, String> idCustDel;
    @FXML
    private TableColumn<Customer, String> nameCustDel;
    @FXML
    private TableColumn<Customer, String> lnameCustDel;
     private Customer c;

    @Inject
    public DeleteCustomerController(CustomerService sc) {
        this.sc = sc;
    }


    public void clickButton(ActionEvent actionEvent) {
        c =listCustDel.getSelectionModel().getSelectedItem();

        sc.delete(c.getIdCustomer());
        textDel.setText(ConstantsScreens.Client_Del);





    }

    @Override
    public void principalCargado() {

        idCustDel.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        ;
        nameCustDel.setCellValueFactory(new PropertyValueFactory<>("name"));
        lnameCustDel.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        sc.getAll()
                .peek(customers -> listCustDel.getItems().addAll(customers))
                .peekLeft(integer -> {
                });


    }
}
