package com.klimkin.dao.jdbc.util;

import com.klimkin.dao.jdbc.AbstractJdbcDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTableUtil extends AbstractJdbcDao {
    private Connection conn;
    private Statement stat;
    private final static String EMPLOYEE =
            "CREATE TABLE Staff (" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "firstName VARCHAR(40) NOT NULL, " +
                    "lastName VARCHAR(40) NOT NULL, " +
                    "email VARCHAR(40) NOT NULL, " +
                    "birthday DATE NOT NULL, " +
                    "salary INT NOT NULL, " +
                    "department_id INT NOT NULL, " +
                    "PRIMARY KEY (ID));";

    private final static String DEPARTMENT =
            "CREATE TABLE Department (" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "departmentName VARCHAR(40) NOT NULL, " +
                    "PRIMARY KEY (ID));";

    private final static String DROP_DEPARTMENT = "DROP TABLE Department;";

    private final static String DROP_EMPLOYEE = "DROP TABLE Staff;";

    private void execute(String query) {
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            stat.execute(query);
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(stat);
            closeConnection(conn);
        }
    }

    public void createDepartmentDb() {
        execute(DEPARTMENT);
    }

    public void createEmployeeDb() {
        execute(EMPLOYEE);
    }

    public void dropDepartmentDb()  {
        execute(DROP_DEPARTMENT);
    }

    public void dropEmployeeDb() {
        execute(DROP_EMPLOYEE);
    }

    public void customQuery(String query) {
        if (query != null) {
            execute(query);
        } else {
            throw new NullPointerException();
        }
    }
}

//jdbc:h2:D:/projects/java/IDEA/Test/corporation/DB/db_corporation