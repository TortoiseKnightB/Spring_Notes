package com.knight.generics.dao;

import com.knight.generics.Plane;
import org.springframework.stereotype.Repository;

@Repository
public class PlaneDao extends BaseDao<Plane> {

    @Override
    public void save() {
        System.out.println("###......PlaneDao");
    }
}
