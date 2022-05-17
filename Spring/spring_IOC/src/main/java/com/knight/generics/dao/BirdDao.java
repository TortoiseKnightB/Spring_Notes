package com.knight.generics.dao;

import com.knight.generics.Bird;
import org.springframework.stereotype.Repository;

@Repository
public class BirdDao extends BaseDao<Bird> {

    @Override
    public void save() {
        System.out.println("###......BirdDao");
    }
}
