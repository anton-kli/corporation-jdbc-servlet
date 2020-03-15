package com.klimkin.dao.jdbc;

import com.klimkin.dao.DepartmentDao;
import com.klimkin.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcDepartmentDao extends AbstractJdbcDao implements DepartmentDao {
    private Connection conn;
    private Statement stat;
    private PreparedStatement prepStat;

    @Override
    public void create(Department department) {
        Objects.requireNonNull(department);
        String sql = "INSERT "
                + "INTO Department (departmentName) "
                + "VALUES(?)";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            prepStat = conn.prepareStatement(sql);
            prepStat.setString(1, department.getDepartmentName());
            prepStat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(prepStat);
            closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM Department WHERE id=?";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            prepStat = conn.prepareStatement(sql);
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(prepStat);
            closeConnection(conn);
        }
    }

    @Override
    public void update(Department department) {
        Objects.requireNonNull(department);
        String sql = "UPDATE Department SET departmentName=? WHERE ID=?";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            prepStat = conn.prepareStatement(sql);
            prepStat.setString(1, department.getDepartmentName());
            prepStat.setInt(2, department.getId());
            prepStat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(prepStat);
            closeConnection(conn);
        }
    }

    @Override
    public List<Department> departmentsList() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM Department";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            conn.setReadOnly(true);
            stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                departments.add(fillDepartment(resultSet));
            }
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(prepStat);
            closeConnection(conn);
        }
        return departments;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Objects.requireNonNull(id);
        Department department = new Department();
        String sql = "SELECT * FROM Department WHERE id='" + id + "'";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            conn.setReadOnly(true);
            stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                department = fillDepartment(resultSet);
            }
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(prepStat);
            closeConnection(conn);
        }
        return department;
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        Objects.requireNonNull(departmentName);
        Department department = new Department();
        String sql = "SELECT * FROM Department WHERE departmentName='" + departmentName + "'";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            conn.setReadOnly(true);
            stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                department = fillDepartment(resultSet);
            }
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(prepStat);
            closeConnection(conn);
        }
        return department;
    }

    @Override
    public boolean compare(String department) {
        Objects.requireNonNull(department);
        List<Department> list = departmentsList();
        for(Department test : list) {
            if (department.equalsIgnoreCase(test.getDepartmentName())) {
                return true;
            }
        }
        return false;
    }

    private Department fillDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setDepartmentName(resultSet.getString("departmentName"));
        return department;
    }
}
