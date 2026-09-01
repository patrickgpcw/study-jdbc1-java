package com.patrick;

import com.patrick.db.DB;
import com.patrick.model.entities.Department;
import com.patrick.model.entities.Seller;


import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department dp = new Department(1, "Book");

        Seller s = new Seller(1, "Patrick", "patrick@pessanha.com", new Date(), 3000.0, dp);

        System.out.println(s);
    }
}