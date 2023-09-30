module javafx11.multipantalla {

//requires

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.apache.logging.log4j;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;

//exports
    exports ui.main to javafx.graphics;
    exports ui.pantallas.principalControllers;
    exports dao.impl;
    exports model.errors;
    exports ui.pantallas.common;
    exports common;
    exports model;

    exports ui.pantallas.customers;
    exports ui.pantallas.orders;
    exports services;

//opens
    opens config;
    opens model.errors;
    opens ui.pantallas.common;
    opens ui.pantallas.orders;
    opens ui.pantallas.customers;
    opens ui.pantallas.principalControllers;
    opens ui.main;
    opens configFiles;

    opens css;
    opens fxml;
    opens services;
    exports dao;


}
