package ui.pantallas.customers;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import services.CustomerService;
import ui.pantallas.common.BaseScreenController;

public class ListCustomerController extends BaseScreenController {

    private final CustomerService sc;
    @FXML
    private TableColumn<Customer, String> dateBirth;
    @FXML
    private TableColumn<Customer, String> idCustomerCol;

    @FXML
    private TableColumn<Customer, String> nameCustomerCol;

    @FXML
    private TableColumn<Customer, String> lastnameCustomerCol;

    @FXML
    private TableColumn<Customer, String> emailCustomerCol;

    @FXML
    private TableColumn<Customer, String> phoneCustomerCol;

    @FXML
    private TableView<Customer> listaCust;

    @Inject
    public ListCustomerController(CustomerService sc) {

        this.sc = sc;

    }

    @Override
    public void principalCargado() {
        idCustomerCol.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        nameCustomerCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameCustomerCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailCustomerCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCustomerCol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        dateBirth.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        sc.getAll()
                .peek(customers -> listaCust.getItems().addAll(customers))
                .peekLeft(integer -> {
                });


    }
}
