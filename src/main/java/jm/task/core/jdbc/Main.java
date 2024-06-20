package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceHy;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceHy userService = new UserServiceHy();
        userService.createUsersTable();
        Util.closeConnection();
        userService.saveUser("John", "Doe", (byte) 50);
        Util.closeConnection();
        userService.saveUser("Jane", "Doe", (byte) 24);
        Util.closeConnection();
        userService.saveUser("Jack", "Smith", (byte) 44);
        Util.closeConnection();
        userService.saveUser("Alex", "Lee", (byte) 26);
        Util.closeConnection();

        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
        Util.closeConnection();

        userService.createUsersTable();
        Util.closeConnection();

        userService.dropUsersTable();
        Util.closeConnection();



    }
}
