module ru.javabegin.javafx.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.controlsfx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires javafx.graphics;

    opens ru.javabegin.javafx.addressbook to javafx.fxml, javafx.graphics;

    exports ru.javabegin.javafx.addressbook.preloader to javafx.graphics;
    exports ru.javabegin.javafx.addressbook;
    exports ru.javabegin.javafx.addressbook.controllers;
    exports ru.javabegin.javafx.addressbook.objects;
    opens ru.javabegin.javafx.addressbook.controllers to javafx.fxml;
}