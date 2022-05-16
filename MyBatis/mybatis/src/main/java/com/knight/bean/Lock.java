package com.knight.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lock {

    private Integer id;
    private String lockName;

    private List<Key> keys;
}
