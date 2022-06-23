package ru.javabegin.javafx.addressbook.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.javabegin.javafx.addressbook.interfaces.AddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

import java.util.Random;

public class CollectionAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    @Override
    public void update(Person person) {
        // т.к. записи хранятся в коллекции, ничего обновлять не нужно, в отличие от БД или файла
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

    public void fillTestData() {
        Random random = new Random();
        for (int i = 1; i < 7; i++) {
            personList.add(newPerson("test" + i, random));
        }
    }

    private Person newPerson(String fio, Random random) {
        return new Person(fio, String.valueOf(random.nextInt(999999, 9999999)));
    }
}
