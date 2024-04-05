package com.vsh8k.mushop.model.Database;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector {

    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DBConnector(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Insert Operation
    public void insert(String tableName, String[] columns, Object[] values) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tableName).append(" (");

        for (int i = 0; i < columns.length; i++) {
            queryBuilder.append(columns[i]);
            if (i < columns.length - 1)
                queryBuilder.append(", ");
        }
        queryBuilder.append(") VALUES (");

        for (int i = 0; i < values.length; i++) {
            queryBuilder.append("?");
            if (i < values.length - 1)
                queryBuilder.append(", ");
        }
        queryBuilder.append(")");

        String query = queryBuilder.toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        }
    }

    // Update Operation
    public void update(String tableName, String setColumn, Object setValue, String conditionColumn, Object conditionValue) throws SQLException {
        String query = "UPDATE " + tableName + " SET " + setColumn + " = ? WHERE " + conditionColumn + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, setValue);
            preparedStatement.setObject(2, conditionValue);
            preparedStatement.executeUpdate();
        }
    }

    // Delete Operation
    public void delete(String tableName, String conditionColumn, Object conditionValue) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE " + conditionColumn + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, conditionValue);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.toString());
        }
    }

    // Query Operation
    public ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
