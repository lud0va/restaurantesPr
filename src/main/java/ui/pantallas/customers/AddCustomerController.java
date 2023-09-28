package ui.pantallas.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import services.CustomerService;
import ui.pantallas.common.BaseScreenController;

import java.time.LocalDate;

public class AddCustomerController extends BaseScreenController {

    private final CustomerService sc;

    @FXML
    private TextField nameAddCust;
    @FXML
    private TextField idAddCust;
    @FXML
    private TextField lastAddCust;
    @FXML
    private TextField phoneAddCust;
    @FXML
    private TextField emailAddCust;
    @FXML
    private TextField birthAddCust;
    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> tableId;
    @FXML
    private TableColumn<Customer, String> tableName;
    @FXML
    private TableColumn<Customer, String> tableLastname;
    @FXML
    private TableColumn<Customer, String> tablePhone;
    @FXML
    private TableColumn<Customer, String> tableBirth;
    @FXML
    private TableColumn<Customer, String> tableEmail;
    @FXML
    private Label custAdded;

    @Inject
    public AddCustomerController(CustomerService sc) {
        this.sc = sc;
    }


    public void clickBtn(ActionEvent actionEvent) {

        if (idAddCust.getText().isEmpty() || nameAddCust.getText().isEmpty() ||lastAddCust.getText().isEmpty() || phoneAddCust.getText().isEmpty() || emailAddCust.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        }else {

            int id = Integer.parseInt(idAddCust.getText());

            int day, month, year;
            String[] birthdate;
            birthdate = birthAddCust.getText().split("-");
            year = Integer.parseInt(birthdate[0]);
            month = Integer.parseInt(birthdate[1]);
            day = Integer.parseInt(birthdate[2]);
            sc.add(new Customer(id, nameAddCust.getText(), lastAddCust.getText(), emailAddCust.getText(), phoneAddCust.getText(), LocalDate.of(year, month, day)));
        }




    }

    @Override
    public void principalCargado() {
        tableId.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        tableBirth.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));


        sc.getAll()
                .peek(customers -> table.getItems().addAll(customers))
                .peekLeft(integer -> {
                });


    }
}
