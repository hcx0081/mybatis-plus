package com.mybatisplus.entity;

import lombok.Getter;

/**
 * {@code @Description:}
 */
@Getter
public enum Sex {
    man("男");
    private String name;
    
    Sex(String name) {
    }
}