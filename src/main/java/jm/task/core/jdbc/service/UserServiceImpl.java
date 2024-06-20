package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoJDBC = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в БД");
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userDaoJDBC.getAllUsers();
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
