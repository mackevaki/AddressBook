package ru.javabegin.javafx.addressbook.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.Main;
import ru.javabegin.javafx.addressbook.interfaces.impls.CollectionAddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import utils.DialogManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private Stage mainStage;

    @FXML
    private AnchorPane anchorPaneForSearch;

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
    private ResourceBundle resourceBundle;
    private ObservableList<Person> backupList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;

        tableAddressBook.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableColumnFio.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));

        txtSearch.setPromptText(resourceBundle.getString("search"));

        initListeners();
        fillData();
        initLoader();
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(Main.class.getResource("edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("ru.javabegin.javafx.addressbook.locales.Locale", new Locale("ru")));
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
        backupList = FXCollections.observableArrayList();
        backupList.addAll(addressBookImpl.getPersonList());
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }

    private void updateCountLabel() {
        labelCount.setText(resourceBundle.getString("count") + ": " + addressBookImpl.getPersonList().size());
    }

    public void actionButtonPressed(ActionEvent event) {
        Object source = event.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        final Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "btnAdd" -> {
                editDialogController.setPerson(new Person());
                showDialog();
                addressBookImpl.add(editDialogController.getPerson());
            }
            case "btnEdit" -> {
                if (!personIsSelected(selectedPerson)) {
                    return;
                }
                editDialogController.setPerson(selectedPerson);
                showDialog();
            }
            case "btnDelete" -> addressBookImpl.delete(selectedPerson);
        }

    }

    private boolean personIsSelected(Person selectedPerson) {
        if (selectedPerson == null) {
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("select_person"));
            return false;
        }
        return true;
    }

    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("editing"));
            editDialogStage.setMinWidth(300);
            editDialogStage.setMinHeight(150);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();
    }

    public void actionSearch(ActionEvent event) {
        addressBookImpl.getPersonList().clear();

        for (Person person :
             backupList) {
            if (person.getFio().toLowerCase().contains(txtSearch.getText().toLowerCase()) || person.getPhoneNumber().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                addressBookImpl.getPersonList().add(person);
            }
        }
    }
}