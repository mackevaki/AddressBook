package ru.javabegin.javafx.addressbook.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private static Connection con;

    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Driver driver = (Driver) Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();

        String url = "jdbc:sqlite:src/main/resources/ru/javabegin/javafx/addressbook/db/AddressBook..db3";

        if (con == null) {
            con = DriverManager.getConnection(url);
        }

        return con;
    }
}
