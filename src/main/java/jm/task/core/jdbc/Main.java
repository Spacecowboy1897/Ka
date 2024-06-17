package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceHy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceHy userService = new UserServiceHy();
        userService.createUsersTable();
        userService.saveUser("John", "Doe", (byte) 50);
        userService.saveUser("Jane", "Doe", (byte) 24);
        userService.saveUser("Jack", "Smith", (byte) 44);
        userService.saveUser("Alex", "Lee", (byte) 26);

        List<User> userList = userService.getAllUsers();
        System.out.println(userList);

        userService.createUsersTable();

        userService.dropUsersTable();

    }
}
