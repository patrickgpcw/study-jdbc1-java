package com.patrick;

import com.patrick.db.DB;
import com.patrick.model.entities.Department;

public class Main {
    public static void main(String[] args) {
        Department dp = new Department(1, "Book");
        System.out.println(dp);
    }
}