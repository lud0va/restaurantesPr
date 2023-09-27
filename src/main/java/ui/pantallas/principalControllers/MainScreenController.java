package ui.pantallas.principalControllers;

import common.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BaseScreenController;

public class MainScreenController extends BaseScreenController {

    @FXML
    private Label welcome;

    public MainScreenController() {

    }

    @Override
    public void principalCargado() {
        welcome.setText(Constants.WELCOME + getPrincipalController().getUser());
        ;

    }
}
