package com.patrick.model.dao;

import com.patrick.db.DB;
import com.patrick.model.impl.DepartmentDaoJDBC;
import com.patrick.model.impl.SellerDaoJDBC;

public class FactoryDao {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }

}
