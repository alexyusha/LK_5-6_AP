package spring.service;

import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static final String USER_NAME = "root";
    private static final String USER_PASS = "root";
    private static final String HOST = "localhost";
    private static final String DB = "insurance_1";

    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {


        Properties pros = new Properties();

        pros.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
        pros.setProperty("url", "jdbc:mysql://" + HOST + "/" + DB);
        pros.setProperty("username", USER_PASS);
        pros.setProperty("password", USER_PASS);

        try {
            dataSource = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource.getConnection();
    }
    @SneakyThrows
    public static void closeConnection(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}

