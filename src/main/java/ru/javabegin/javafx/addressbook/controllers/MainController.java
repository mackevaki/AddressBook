package ru.javabegin.javafx.addressbook.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.Main;
import ru.javabegin.javafx.addressbook.interfaces.impls.CollectionAddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

import java.io.IOException;

public class MainController {
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private TableColumn<Person, String> tableColumnFio;

    @FXML
    private TableColumn<Person, String> tableColumnPhone;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView tableAddressBook;

    @FXML
    private Label labelCount;

    @FXML
    private void initialize() {
        tableAddressBook.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableColumnFio.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));

        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        addressBookImpl.fillTestData();

        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    public void showDialog(ActionEvent event) {
        Object source = event.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "btnAdd" -> System.out.println("add " + selectedPerson);
            case "btnEdit" -> System.out.println("edit " + selectedPerson);
            case "btnDelete" -> System.out.println("delete " + selectedPerson);
        }
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("edit.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinWidth(300);
            stage.setMinHeight(150);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}