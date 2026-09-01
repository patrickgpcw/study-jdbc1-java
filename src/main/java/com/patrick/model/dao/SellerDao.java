package com.patrick.model.dao;

import com.patrick.model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller seller);

    void update(Seller seller);

    void delete(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll();
}
