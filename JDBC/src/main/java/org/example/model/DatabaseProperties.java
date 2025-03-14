package org.example.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    public static void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/db.properties")); // Убедитесь в правильности пути
            jdbcURL = properties.getProperty("jdbc.url");
            jdbcUsername = properties.getProperty("jdbc.username");
            jdbcPassword = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJdbcURL() {
        if(jdbcURL == null)
            loadProperties();
        return jdbcURL;
    }

    public static String getJdbcUsername() {
        if(jdbcUsername == null)
            loadProperties();
        return jdbcUsername;
    }

    public static String getJdbcPassword() {
        if(jdbcPassword == null)
            loadProperties();
        return jdbcPassword;
    }
}