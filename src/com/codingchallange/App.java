package com.codingchallange;

import com.codingchallange.data.DataStore;
import com.codingchallange.data.FileBasedDataStore;
import com.codingchallange.service.BeerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        FileBasedDataStore dataStore = new FileBasedDataStore();
        dataStore.init("resources/beers.json");
        BeerService beerService = new BeerService(dataStore);
        System.out.println("Task 1:");
        writeOutWithPrettyJsonString(beerService.getBeersByBrand());
        System.out.println("\nTask 2:");
        writeOutWithPrettyJsonString(beerService.getBrandsByBeerType("Világos"));
        System.out.println("\nTask 3:");
        writeOutWithPrettyJsonString(beerService.getCheapestBrand());
        System.out.println("\nTask 4:");
        writeOutWithPrettyJsonString(beerService.getBeersWhichLackOfIngredient("só"));
        System.out.println("\nTask 5:");
        writeOutWithPrettyJsonString(beerService.listBeersSortedByRemainingRatio());
        System.out.println();
        beerService.WriteOutRemainingRatioForBeers();
    }

    public static void writeOutWithPrettyJsonString(Object jsonObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));
    }
}
