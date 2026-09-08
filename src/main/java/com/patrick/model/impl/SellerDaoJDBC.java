package com.patrick.model.impl;

import com.patrick.db.DbException;
import com.patrick.model.dao.SellerDao;
import com.patrick.model.entities.Department;
import com.patrick.model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn = null;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {
        String sql = "INSERT INTO seller"
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                + "VALUE "
                + "(?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, seller.getName());
            ps.setString(2, seller.getEmail());
            ps.setDate(3, new Date(seller.getBirthDate().getTime()));
            ps.setDouble(4, seller.getBaseSalary());
            ps.setInt(5, seller.getDepartment().getId());
            int rowAffects = ps.executeUpdate();
            if (rowAffects > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    seller.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DbException("Error to insert seller: ", e);
        }

    }

    @Override
    public void update(Seller seller) {
        String sql = "UPDATE seller "
                + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                + "WHERE Id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, seller.getName());
            ps.setString(2, seller.getEmail());
            ps.setDate(3, new Date(seller.getBirthDate().getTime()));
            ps.setDouble(4, seller.getBaseSalary());
            ps.setInt(5, seller.getDepartment().getId());
            ps.setInt(6, seller.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Error to update seller: ", e);
        }

    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM seller "
                + "WHERE "
                + "(Id = ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error trying delete seller:", e);
        }
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
            if (rs.next()) {
                Department dp = instantiateDepartment(rs);
                return instantiateSeller(rs, dp);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Error to find seller: ", e);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dp = new Department();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("DepName"));
        return dp;
    }

    private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
        Seller s = new Seller();
        s.setName(rs.getString("Name"));
        s.setEmail(rs.getString("Email"));
        s.setBirthDate(rs.getDate("BirthDate"));
        s.setBaseSalary(rs.getDouble("BaseSalary"));
        s.setDepartment(dp);
        return s;
    }

    @Override
    public List<Seller> findAll() {
        String sql = "SELECT seller.*,department.Name as DepName "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.Id "
                + "ORDER BY Name";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller s = instantiateSeller(rs, dep);
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException("Error to findAll sellers:", e);
        }
    }
}
