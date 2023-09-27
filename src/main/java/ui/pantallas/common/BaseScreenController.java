package ui.pantallas.common;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CustomerService;
import ui.pantallas.principalControllers.PrincipalController;


public abstract class BaseScreenController {

    private PrincipalController principalController;


    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado() {

    }

    ;

}
