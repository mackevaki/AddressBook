package ru.javabegin.javafx.addressbook.interfaces;

import ru.javabegin.javafx.addressbook.objects.Person;

public interface AddressBook {
    void add(Person person);

    void delete(Person person);

    void update(Person person);
}
