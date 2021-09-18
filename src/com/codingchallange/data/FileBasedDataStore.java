package com.codingchallange.data;

import com.codingchallange.domain.Beer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileBasedDataStore implements DataStore {
    private List<Beer> beers;

    public FileBasedDataStore() {
        beers = new ArrayList<>();
    }

    public void init(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        beers = mapper.readValue(new File(filePath), new TypeReference<List<Beer>>() {
        });
    }

    @Override
    public List<Beer> getBeers() {
        return beers.stream().toList();
    }
}
