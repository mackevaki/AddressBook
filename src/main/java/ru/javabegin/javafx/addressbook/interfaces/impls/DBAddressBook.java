package ru.javabegin.javafx.addressbook.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.javabegin.javafx.addressbook.db.SQLiteConnection;
import ru.javabegin.javafx.addressbook.interfaces.AddressBook;
import ru.javabegin.javafx.addressbook.objects.Person;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DBAddressBook implements AddressBook {
    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public boolean add(Person person) {
        try(Connection con = SQLiteConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into person(credentials, phone_number) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, person.getFio());
            stmt.setString(2, person.getPhoneNumber());

            int result = stmt.executeUpdate();
            if (result > 0) {
                int id = stmt.getGeneratedKeys().getInt(1); // получить сгенерированный id вставленной записи
                person.setId(id);
                personList.add(person);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Person person) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement stmt = con.createStatement()){
            int result = stmt.executeUpdate("delete from person where id = " + person.getId());
            if (result > 0) {
                personList.remove(person);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Person person) {
        try (Connection con = SQLiteConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("update person set credentials = ?, phone_number = ? where id = ?")) {
            stmt.setString(1, person.getFio());
            stmt.setString(2, person.getPhoneNumber());
            stmt.setInt(3, person.getId());
            int result = stmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        personList.clear();

        try (Connection con = SQLiteConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from person where credentials like ? or phone_number like ?")) {
            String searchStr = "%"+text+"%";
            stmt.setString(1, searchStr);
            stmt.setString(2, searchStr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fio = rs.getString("credentials");
                String phone_number = rs.getString("phone_number");
                personList.add(new Person(id, fio, phone_number));
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
}
