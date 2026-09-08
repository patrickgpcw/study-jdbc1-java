package com.patrick;

import com.patrick.model.dao.FactoryDao;
import com.patrick.model.dao.SellerDao;
import com.patrick.model.entities.Department;
import com.patrick.model.entities.Seller;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        System.out.println("=== TEST 3: seller insert ===");
        Department department = new Department(1, "Computers");
        Seller seller = new Seller(null,
                "Marcelo Junior",
                "marcelo@junior.com",
                Date.valueOf(LocalDate.of(1995, 12, 27)),
                3000.0,
                department);

        SellerDao sellerDao = FactoryDao.createSellerDao();
        sellerDao.insert(seller);

        System.out.println( "The seller " +seller.getName() + " was inserted!");



//        System.out.println("=== TEST 1: seller findById ===");
//        Seller s = sellerDao.findById(8);
//        System.out.println(s);
//        System.out.println("=== TEST 2: seller delete ===");


        sellerDao.delete(1);
    }
}