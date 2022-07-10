package ru.javabegin.javafx.addressbook.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
        String url = "jdbc:sqlite:src/main/resources/ru/javabegin/javafx/addressbook/db/AddressBook..db3";
        return DriverManager.getConnection(url);
    }
}
