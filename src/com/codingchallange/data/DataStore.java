package com.codingchallange.data;

import com.codingchallange.domain.Beer;

import java.util.List;

public interface DataStore {
    List<Beer> getBeers();
}
