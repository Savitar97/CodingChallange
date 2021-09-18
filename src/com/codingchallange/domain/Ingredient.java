package com.codingchallange.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ingredient {
    private String id;
    private double ratio;
    private String name;
}
