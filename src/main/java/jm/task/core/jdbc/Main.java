package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {


        userService.createUsersTable();

        userService.saveUser("Павел", "Валуев", (byte) 34);
        userService.saveUser("Михаил", "Валуев", (byte) 64);
        userService.saveUser("Максим", "Бусарев", (byte) 22);
        userService.saveUser("Сергей", "Петров", (byte) 26);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(1);
        userService.removeUserById(2);
        userService.removeUserById(3);
        userService.removeUserById(4);

        userService.dropUsersTable();
    }
}