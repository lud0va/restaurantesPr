package ui.pantallas.customers;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import services.CustomerService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.ConstantsScreens;

public class ModifyCustomerController extends BaseScreenController {
    private final CustomerService sc;
    @FXML
    private Label labelMod;
    @FXML
    private TextField idModify;
    @FXML
    private TextField nameModify;
    @FXML
    private TextField lnameModify;
    @FXML
    private TextField phoneModify;
    @FXML
    private TextField emailModify;
    @FXML
    private TextField bithModify;
    @FXML
    private TableView<Customer> tableModify;
    @FXML
    private TableColumn<Customer, String> idModTab;
    @FXML
    private TableColumn<Customer, String> nameModTab;
    @FXML
    private TableColumn<Customer, String> lnameModTab;
    @FXML
    private TableColumn<Customer, String> phoneModTab;
    @FXML
    private TableColumn<Customer, String> emailModTab;
    @FXML
    private TableColumn<Customer, String> birthModTab;

    @Inject
    public ModifyCustomerController(CustomerService sc) {

        this.sc = sc;
    }

    public void clickTable(MouseEvent mouseEvent) {

        Customer c = tableModify.getSelectionModel().getSelectedItem();
        idModify.setText(String.valueOf(c.getIdCustomer()));
        nameModify.setText(c.getName());
        lnameModify.setText(c.getLastname());
        phoneModify.setText(c.getPhonenumber());
        emailModify.setText(c.getEmail());
        bithModify.setText(c.getBirthdayDate().toString());

    }

    @Override
    public void principalCargado() {
        idModTab.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        nameModTab.setCellValueFactory(new PropertyValueFactory<>("name"));
        lnameModTab.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailModTab.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneModTab.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        birthModTab.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));

        sc.getAll()
                .peek(customers -> tableModify.getItems().addAll(customers))
                .peekLeft(integer -> {
                });

    }

    public void addCustMod(ActionEvent actionEvent) {
        try {
            Customer c = tableModify.getSelectionModel().getSelectedItem();
            sc.update(c);
            labelMod.setText(ConstantsScreens.Client_Upd);


        } catch (Exception e) {
            labelMod.setText(ConstantsScreens.ELEMENTS_REQUIRED);
        }

    }
}
