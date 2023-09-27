package ui.pantallas.principalControllers;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Data;
import model.Credential;
import services.CredentialService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.ConstantsScreens;

@Data
public class LoginController extends BaseScreenController {

    @FXML
    private Label userERR;
    @FXML
    private TextField userLog;
    @FXML
    private TextField passLog;

    private final CredentialService se;

    @Inject
    public LoginController(CredentialService se) {
        this.se = se;
    }


    @FXML
    private void login(ActionEvent actionEvent) {
        Credential ce = new Credential(userLog.getText(), passLog.getText());
        if (se.getCredential().equals(ce)) {
            getPrincipalController().onLogin(userLog.getText());
        } else {
            userERR.setText(ConstantsScreens.User_Inv);
        }

    }
}
