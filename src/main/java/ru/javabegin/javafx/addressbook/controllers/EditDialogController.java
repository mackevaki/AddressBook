package ru.javabegin.javafx.addressbook.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.objects.Person;
import utils.DialogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDialogController implements Initializable {

    @FXML
    private Label labelPhone;

    @FXML
    private Label labelFIO;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtFIO;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    private Person person;
    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }
    public void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent event) {
        if (!checkValues()) {
            return;
        }
        person.setFio(txtFIO.getText());
        person.setPhoneNumber(txtPhoneNumber.getText());
        actionClose(event);
    }

    private boolean checkValues() {
        if (txtFIO.getText().trim().length() == 0 || txtPhoneNumber.getText().trim().length() == 0) {
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("fill_field"));
            return false;
        }
        return true;
    }

    public void setPerson(Person person) {
        if (person == null) {
            return;
        }
        this.person = person;
        txtFIO.setText(person.getFio());
        txtPhoneNumber.setText(person.getPhoneNumber());
    }

    public Person getPerson() {
        return person;
    }
}
