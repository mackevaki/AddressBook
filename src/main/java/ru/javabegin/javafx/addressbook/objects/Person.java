package ru.javabegin.javafx.addressbook.objects;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty phoneNumber = new SimpleStringProperty("");

    public Person() {}

    public Person(String fio, String phoneNumber) {
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

    public SimpleStringProperty fioProperty() {
        return fio;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
