package com.csl.controller;

import org.hibernate.validator.constraints.Length;

public class TestBo {
    @Length(min = 1, max = 3,message = "name 1-3")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
