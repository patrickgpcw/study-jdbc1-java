package com.patrick;

import com.patrick.model.dao.FactoryDao;
import com.patrick.model.dao.SellerDao;
import com.patrick.model.entities.Seller;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = FactoryDao.createSellerDao();


        System.out.println("=== TEST 1: seller findById ===");
        Seller s = sellerDao.findById(8);

        System.out.println(s);
    }
}