package com.patrick.model.impl;

import com.patrick.db.DbException;
import com.patrick.model.dao.DepartmentDao;
import com.patrick.model.entities.Department;

import java.lang.reflect.GenericArrayType;
import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection conn = null;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department d) {
        String sql = "INSERT INTO department"
                + "(Name) "
                + "VALUE "
                + "(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getName());
            int rowAffects = ps.executeUpdate();
            if (rowAffects > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    d.setId(id);
                }
            }
            System.out.println("New department created");
        } catch (SQLException e) {
            throw new DbException("Error to insert new department: ", e);
        }
    }

    @Override
    public void update(Department d) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
