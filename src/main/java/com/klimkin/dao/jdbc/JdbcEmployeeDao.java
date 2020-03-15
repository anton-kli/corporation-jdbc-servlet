package com.klimkin.dao.jdbc;

import com.klimkin.dao.EmployeeDao;
import com.klimkin.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcEmployeeDao extends AbstractJdbcDao implements EmployeeDao {
    private Connection conn;
    private Statement stat;
    private PreparedStatement prepStat;

    @Override
    public void create(Employee employee) {
        Objects.requireNonNull(employee);
        String sql = "INSERT "
                + "INTO Staff (firstName, lastName, email, birthday, salary, department_id) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            prepStat = conn.prepareStatement(sql);
            prepStat = prepareEmployee(prepStat, employee);
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
        String sql = "DELETE FROM Staff WHERE id=?";
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
    public void update(Employee employee) {
        Objects.requireNonNull(employee);
        String sql = "UPDATE Staff SET firstName=?, lastName=?, " +
                "email=?, birthday=?, salary=?, department_id=? WHERE ID=?";
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            prepStat = conn.prepareStatement(sql);
            prepStat = prepareEmployee(prepStat, employee);
            prepStat.setInt(7, employee.getId());
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
    public List<Employee> staffList() {
        String sql = "SELECT * FROM Staff";
        return getStaff(sql);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM Staff WHERE id="+id;
        return getEmployee(sql);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        String sql = "SELECT * FROM Staff WHERE email='"+email+"'";
        return getEmployee(sql);
    }

    @Override
    public List<Employee> findEmployeeByDepartment(Integer department_id) {
        String sql = "SELECT * FROM Staff WHERE department_id="+department_id;
        return getStaff(sql);
    }

    @Override
    public boolean compare(String email) {
        Objects.requireNonNull(email);
        Employee test = findEmployeeByEmail(email);
        return test.getId() != null;
    }

    private Employee fillEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        JdbcDepartmentDao departmentDao = new JdbcDepartmentDao();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setLastName(resultSet.getString("lastName"));
        employee.setEmail(resultSet.getString("email"));
        employee.setBirthday(resultSet.getDate("birthday"));
        employee.setSalary(resultSet.getInt("salary"));
        employee.setDepartment(departmentDao.findDepartmentById(resultSet.getInt("department_id")));
        return employee;

    }

    private PreparedStatement prepareEmployee(PreparedStatement prepStat, Employee e) throws SQLException {
        PreparedStatement ps = prepStat;
        ps.setString(1, e.getFirstName());
        ps.setString(2, e.getLastName());
        ps.setString(3, e.getEmail());
        ps.setDate(4, new java.sql.Date(e.getBirthday().getTime()));
        ps.setInt(5, e.getSalary());
        ps.setInt(6, e.getDepartment().getId());
        return ps;
    }

    private Employee getEmployee(String sql) {
        Employee employee = new Employee();
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            conn.setReadOnly(true);
            stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                employee = fillEmployee(resultSet);
            }
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(stat);
            closeConnection(conn);
        }
        return employee;
    }

    private List<Employee> getStaff(String sql) {
        List<Employee> staff = new ArrayList<>();
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            conn.setReadOnly(true);
            stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                staff.add(fillEmployee(resultSet));
            }
            conn.commit();
        } catch (SQLException e) {
            rollbackConnection(conn);
            e.printStackTrace();
        } finally {
            closeStatement(stat);
            closeConnection(conn);
        }
        return staff;
    }
}
