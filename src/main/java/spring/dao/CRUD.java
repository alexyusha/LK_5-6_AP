package spring.dao;

import spring.service.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class CRUD<T> {
    abstract long create(T t);

    abstract T read(String numberContract);

    abstract void update(T t);

    //abstract void setValue(PreparedStatement preparedStatement, T t);

    void delete(String value, String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, null);
        }
    }

    String checkUniqueness(String value, String sql, String checkName) {
        String temp = value;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            set = statement.executeQuery();
            while (set.next()) {
                if (set.getString(checkName).equals(value)) {
                    System.out.println("Поле с таким "+ checkName + " существует.");
                    temp = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(statement, connection, set);
        }

        return temp;
    }

    long getId(String value, String sql, String nameCheck) {
        long id = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            set = statement.executeQuery();
            while (set.next()) {
                if (set.getString(nameCheck).equals(value)) {
                    id = set.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(statement, connection, set);
        }
        return id;
    }
}
