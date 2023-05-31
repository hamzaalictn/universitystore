package com.universitystore.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DBUtils {
    public static List<Map<String, Object>> executeQuery(String host, int port, String database, String dbUser, String dbPassword, String query) {
        String dbUrl = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            List<Map<String, Object>> result = new ArrayList<>();
            ResultSetMetaData rsmd = resultSet.getMetaData();

            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                result.add(colNameValueMap);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error executing SQL query: " + query, e);
        }
    }
}
