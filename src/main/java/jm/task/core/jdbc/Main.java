package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Павел", "Валуев", (byte) 34);
        userDaoHibernate.saveUser("Михаил", "Валуев", (byte) 64);
        userDaoHibernate.saveUser("Максим", "Бусарев", (byte) 22);
        userDaoHibernate.saveUser("Сергей", "Петров", (byte) 26);

        System.out.println(userDaoHibernate.getAllUsers());

        userDaoHibernate.removeUserById(1);
        userDaoHibernate.removeUserById(2);
        userDaoHibernate.removeUserById(3);
        userDaoHibernate.removeUserById(4);

        userDaoHibernate.dropUsersTable();
    }
}
