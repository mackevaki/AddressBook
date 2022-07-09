package ru.javabegin.javafx.addressbook.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty phoneNumber = new SimpleStringProperty("");

    public Person() {}

    public Person(int id, String fio, String phoneNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.fio = new SimpleStringProperty(fio);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public String getFio() {
        return fio.get();
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleStringProperty fioProperty() {
        return fio;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
