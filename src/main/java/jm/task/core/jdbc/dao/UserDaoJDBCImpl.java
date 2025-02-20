package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(" + "ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), " + "LASTNAME VARCHAR(100), AGE INT, PRIMARY KEY (ID) )";

        try (Statement stat = connection.createStatement()) {

            stat.executeUpdate(sql);
            System.out.println("Table was created!");

        } catch (SQLException throwables) {
            System.out.println("Error creating table: " + throwables.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Statement stat = connection.createStatement()) {

            stat.executeUpdate(sql);
            System.out.println("Table was dropped");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //preStat.setLong(1, 1);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User was added!");

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID=?";

        try (PreparedStatement preStat = connection.prepareStatement(sql)) {

            preStat.setLong(1, id);

            preStat.executeUpdate();
            System.out.println("User was removed!");

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";

        try (Statement stat = connection.createStatement()) {

            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
            System.out.println("List of users is ready!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;

    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Statement stat = connection.createStatement()) {

            stat.executeUpdate(sql);
            System.out.println("Table was cleaned!");

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}