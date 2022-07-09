package ru.javabegin.javafx.addressbook.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.javabegin.javafx.addressbook.interfaces.AddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

public class CollectionAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public boolean add(Person person) {
        personList.add(person);
        return true;
    }

    @Override
    public boolean delete(Person person) {
        personList.remove(person);
        return true;
    }

    @Override
    public boolean update(Person person) {
        // т.к. записи хранятся в коллекции, ничего обновлять не нужно, в отличие от БД или файла
        return true;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void printCollection() {
        int number = 0;
        for (Person person :
                personList) {
            System.out.println(++number + ") fio: " + person.getFio() + "; phoneNumber: " + person.getPhoneNumber());
        }
    }


    @Override
    public ObservableList<Person> findAll() {
        return null;
    }

    @Override
    public ObservableList<Person> find(String text) {
        return null;
    }
}
