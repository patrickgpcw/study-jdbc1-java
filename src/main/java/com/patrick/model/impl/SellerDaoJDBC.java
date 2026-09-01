package com.patrick.model.impl;

import com.patrick.model.dao.SellerDao;
import com.patrick.model.entities.Seller;

import java.util.List;

public class SellerDaoJDBC implements SellerDao {

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
        return null;
    }

    @Override
    public List<Seller> findAll() {

        return List.of();
    }
}
