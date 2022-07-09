package ru.javabegin.javafx.addressbook.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.javabegin.javafx.addressbook.db.SQLiteConnection;
import ru.javabegin.javafx.addressbook.interfaces.AddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAddressBook implements AddressBook {
    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public boolean add(Person person) {
        return false;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }

    @Override
    public boolean update(Person person) {
        return false;
    }

    @Override
    public ObservableList<Person> findAll() {
        try (Connection con = SQLiteConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from person")) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setFio(rs.getString("credentials"));
                person.setPhoneNumber(rs.getString("phone_number"));
                personList.add(person);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public ObservableList<Person> find(String text) {
        return null;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
}
