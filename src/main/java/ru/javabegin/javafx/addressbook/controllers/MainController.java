package ru.javabegin.javafx.addressbook.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.Main;
import ru.javabegin.javafx.addressbook.interfaces.impls.CollectionAddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

import java.io.IOException;

public class MainController {
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private Stage mainStage;

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

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() {
        tableAddressBook.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableColumnFio.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));

        initListeners();
        fillData();
        initLoader();
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(Main.class.getResource("edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        tableAddressBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }


    private void fillData() {
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    public void actionButtonPressed(ActionEvent event) {
        Object source = event.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;


//        Window parentWindow = ((Node)event.getSource()).getScene().getWindow();

        switch (clickedButton.getId()) {
            case "btnAdd" -> {
                editDialogController.setPerson(new Person());
                showDialog();
                addressBookImpl.add(editDialogController.getPerson());

            }
            case "btnEdit" -> {
                editDialogController.setPerson((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
            }
            case "btnDelete" -> addressBookImpl.delete((Person) tableAddressBook.getSelectionModel().getSelectedItem());
        }

    }

    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinWidth(300);
            editDialogStage.setMinHeight(150);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();
    }
}