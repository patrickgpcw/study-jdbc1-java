package com.patrick;

import com.patrick.db.DB;

public class Main {
    public static void main(String[] args) {

//        DB.printDepartmentTable();
//        DB.insertNewSeller();
//        DB.updateBaseSalarySeller();
//        DB.insetNewDepartment();
//        DB.removeDepartment();
        DB.transactionBaseSalaryFromSeller();
    }
}