package com.patrick.model.dao;

import com.patrick.model.entities.Department;
import com.patrick.model.impl.SellerDaoJDBC;

public class FactoryDao {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC();
    }
    
}
