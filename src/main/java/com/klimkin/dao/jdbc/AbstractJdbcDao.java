package com.klimkin.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class AbstractJdbcDao {
    private String jdbcDriver;
    private String url;
    private String role;
    private String password;
    private String path = "./src/main/resources/db.properties";
    private String testPath = "./src/main/resources/db.properties";

    private Properties props = null;
    private Connection conn;

    public boolean testFlag = false;

    public Connection createConnection() {
        props = new Properties();
        if (testFlag) {
            path = testPath;
        }
        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            props.load(in);
            jdbcDriver = props.getProperty("db.driver");
            url = props.getProperty("db.url");
            role = props.getProperty("db.username");
            password = props.getProperty("db.password");
        } catch (IOException io) {
            io.printStackTrace();
        }

        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(url, role, password);
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    protected void rollbackConnection(Connection c) {
        try {
            conn.rollback();
        } catch (SQLException ignore) {
        }
    }

    protected void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException ignore) {
        }
    }

    protected void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException ignore) {
        }
    }
}
