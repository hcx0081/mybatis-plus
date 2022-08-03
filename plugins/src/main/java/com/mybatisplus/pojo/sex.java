package com.mybatisplus.pojo;

import lombok.Getter;

/**
 * @Description:
 */
@Getter
public enum sex {
    man("男");
    private String name;
    
    sex(String name) {
    }
}
