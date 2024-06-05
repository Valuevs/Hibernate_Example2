package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        // Создает таблицу users в вашей базе данных.
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45), lastName VARCHAR(45), age TINYINT)");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println("Ошибка при создании таблицы users: " + e.getMessage());
        }finally {
            if (connection != null) {}
        }
    }

    public void dropUsersTable() {
        // УДАЛЯЕТ ТАБЛИЦУ ИЗ БАЗЫ ДАННЫХ
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate("DROP TABLE IF EXISTS User");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println("Ошибка при удалении таблицы: " + e.getMessage());
        }finally {
            if (connection != null) {}
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // Вставляет новую запись пользователя в таблицу users.
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println("Ошибка при сохранении  пользователя: " + e.getMessage());
        }finally {
            if (connection != null) {}
        }
    }

    public void removeUserById(long id) {
        // Удаляет запись пользователя по его ID из таблицы users.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?");
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println("Ошибка при удалении: " + e.getMessage());
        }finally {
            if (connection != null) {}
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }finally {
            if (connection != null) {}
        }
        return users;
    }

    public void cleanUsersTable() {
        // Очищает таблицу users, удаляя все записи.
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.execute("TRUNCATE TABLE User");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Таблица не очистилась: " + e.getMessage());
        }finally {
            if (connection != null) {}
        }
    }
}
