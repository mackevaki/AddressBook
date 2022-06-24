package ru.javabegin.javafx.addressbook.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.javabegin.javafx.addressbook.objects.Person;

public class EditDialogController {

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

    public void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent event) {
        person.setFio(txtFIO.getText());
        person.setPhoneNumber(txtPhoneNumber.getText());
        actionClose(event);
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
