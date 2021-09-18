package com.codingchallange.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Beer {
    private String type;
    private String name;
    private String id;
    private String brand;
    private double price;
    private double alcohol;
    private List<Ingredient> ingredients;
    private boolean isCan;

    public void setisCan(boolean can) {
        isCan = can;
    }
}
