package com.patrick;

import com.patrick.model.dao.DepartmentDao;
import com.patrick.model.dao.FactoryDao;
import com.patrick.model.entities.Department;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        SellerDao sellerDao = FactoryDao.createSellerDao();
        DepartmentDao departmentDao = FactoryDao.createDepartmentDao();
        Department department = new Department(6, "Foods");
//        Seller seller = new Seller(9,
//                "Patrick Pessanha",
//                "patrick.pessanha@gmail.com",
//                Date.valueOf(LocalDate.of(1995, 12, 27)),
//                5000.0,
//                department);

//        System.out.println("=== TEST 1: seller findById ===");
//        Seller s = sellerDao.findById(8);
//        System.out.println(s);

//        System.out.println("=== TEST 2: seller delete ===");
//        sellerDao.delete(1);
//
//        System.out.println("=== TEST 3: seller insert ===");
//        sellerDao.insert(seller);
//        System.out.println( "The seller " + seller.getName() + " was inserted!");

//        System.out.println("=== TEST 4: seller findById ===");
//        List<Seller> sellers = sellerDao.findAll();
//        for (Seller s : sellers) {
//            System.out.println(s);
//        }

//        System.out.println("=== TEST 5: seller findByDepartment ===");
//        List<Seller> sellers = sellerDao.findByDepartment(department);
//        for (Seller s : sellers) {
//            System.out.println(s);
//        }
//        System.out.println("=== TEST 6: department insert ===");
//        departmentDao.insert(department);

//        System.out.println("=== TEST 7: department update ===");
//        departmentDao.update(department);

        System.out.println("=== TEST 8: department delete ===");
        departmentDao.delete(department.getId());


    }
}