package com.patrick;

import com.patrick.model.dao.FactoryDao;
import com.patrick.model.dao.SellerDao;
import com.patrick.model.entities.Department;
import com.patrick.model.entities.Seller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = FactoryDao.createSellerDao();
        Department department = new Department(1, "Computers");
        Seller seller = new Seller(9,
                "Patrick Pessanha",
                "patrick.pessanha@gmail.com",
                Date.valueOf(LocalDate.of(1995, 12, 27)),
                5000.0,
                department);

//        System.out.println("=== TEST 1: seller findById ===");
//        Seller s = sellerDao.findById(8);
//        System.out.println(s);

//        System.out.println("=== TEST 2: seller delete ===");
//        sellerDao.delete(1);
//
//        System.out.println("=== TEST 3: seller insert ===");
//        sellerDao.insert(seller);
//        System.out.println( "The seller " + seller.getName() + " was inserted!");

        System.out.println("=== TEST 4: seller findById ===");
        List<Seller> sellers = sellerDao.findAll();
        for (Seller s : sellers) {
            System.out.println(s);
        }
    }
}