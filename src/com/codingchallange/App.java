package com.codingchallange;

import com.codingchallange.data.DataStore;
import com.codingchallange.data.FileBasedDataStore;
import com.codingchallange.service.BeerService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        FileBasedDataStore dataStore = new FileBasedDataStore();
        dataStore.init("resources/beers.json");
        BeerService beerService = new BeerService(dataStore);
        System.out.println(beerService.getBeersByBrand());
        System.out.println(beerService.getBrandsByBeerType("Világos"));
        System.out.println(beerService.getCheapestBrand());
        System.out.println(beerService.getBeersWhichLackOfIngredient("só"));
        System.out.println(beerService.listBeersSortedByRemainingRatio());
        beerService.WriteOutRemainingRatioForBeers();
    }
}
