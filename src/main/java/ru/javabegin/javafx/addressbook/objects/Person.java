package ru.javabegin.javafx.addressbook.objects;

public class Person {
    private String fio;
    private String phoneNumber;

    public Person() {}

    public Person(String fio, String phoneNumber) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
