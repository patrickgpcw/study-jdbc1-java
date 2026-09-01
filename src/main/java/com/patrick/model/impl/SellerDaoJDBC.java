package com.patrick.model.impl;

import com.patrick.db.DbException;
import com.patrick.model.dao.DepartmentDao;
import com.patrick.model.dao.SellerDao;
import com.patrick.model.entities.Department;
import com.patrick.model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn = null;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        String sql = "SELECT seller.*, department.Name as DepName "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.Id "
                + "WHERE seller.Id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Department dp = new Department();
                dp.setId(rs.getInt("DepartmentId"));
                dp.setName(rs.getString("DepName"));
                Seller s = new Seller();
                s.setName(rs.getString("Name"));
                s.setEmail(rs.getString("Email"));
                s.setBirthDate(rs.getDate("BirthDate"));
                s.setBaseSalary(rs.getDouble("BaseSalary"));
                s.setDepartment(dp);
                return s;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Error to find seller: ", e);
        }
    }

    @Override
    public List<Seller> findAll() {

        return List.of();
    }
}
