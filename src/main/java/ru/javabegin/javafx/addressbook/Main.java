package ru.javabegin.javafx.addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.controllers.MainController;
import ru.javabegin.javafx.addressbook.interfaces.impls.CollectionAddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = new MainController();
        mainController.setMainStage(stage);

        Scene scene = new Scene(fxmlMain, 320, 240);
        stage.setTitle("Address Book");
        stage.setMinHeight(500);
        stage.setMinWidth(400);
        stage.setScene(scene);
        stage.show();

    }

    private void testData() {
        CollectionAddressBook addressBook = new CollectionAddressBook();

        addressBook.fillTestData();
        addressBook.printCollection();
    }


    public static void main(String[] args) {
        launch();
    }
}