package ru.javabegin.javafx.addressbook.interfaces;

import javafx.collections.ObservableList;
import ru.javabegin.javafx.addressbook.objects.Person;

public interface AddressBook {
    boolean add(Person person);

    boolean delete(Person person);

    boolean update(Person person);

    ObservableList<Person> findAll();

    ObservableList<Person> find(String text);
}
