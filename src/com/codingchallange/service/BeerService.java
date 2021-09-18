package com.codingchallange.service;

import com.codingchallange.data.DataStore;
import com.codingchallange.domain.Beer;
import com.codingchallange.domain.Ingredient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class BeerService {
    private final DataStore dataStore;

    public BeerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Map<String, List<Beer>> getBeersByBrand() {
        return dataStore.getBeers().stream()
                .collect(Collectors
                        .groupingBy(Beer::getType));
    }

    public Set<String> getBrandsByBeerType(String beerType){
        Predicate<Beer> beerTypeFilter = beer -> beerType.equalsIgnoreCase(beer.getType());
        return dataStore.getBeers().stream()
                .filter(beerTypeFilter)
                .map(Beer::getBrand)
                .collect(Collectors.toSet());
    }

    public String getCheapestBrand() {
        Predicate<Map.Entry<String, Double>> thePriceIsTheCheapest
                = entry -> getTheCheapestBrandPrice() == entry.getValue();
        return getAveragePriceForBrands().entrySet().stream()
                .filter(thePriceIsTheCheapest)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse("");
    }

    private double getTheCheapestBrandPrice() {
        return getAveragePriceForBrands().values().stream()
                .min(Double::compareTo).orElse(0.0);
    }

    private Map<String,Double> getAveragePriceForBrands(){
        return dataStore.getBeers().stream()
                .collect(Collectors
                        .groupingBy(Beer::getBrand,Collectors.averagingDouble(Beer::getPrice)));
    }

    public List<Beer> getBeersWhichLackOfIngredient(String ingredient){
        Predicate<Beer> beerNotContainsTheSpecificIngredient
                = beer -> !isContainsIngredient(ingredient, beer);
        return dataStore.getBeers().stream()
                .filter(beerNotContainsTheSpecificIngredient)
                .toList();
    }

    private boolean isContainsIngredient(String ingredient, Beer beer) {
        return getIngredientsNamesLowerCase(beer)
                .contains(ingredient.toLowerCase());
    }

    private Set<String> getIngredientsNamesLowerCase(Beer beer) {
        return beer.getIngredients().stream()
                .map(Ingredient::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    public List<Beer> listBeersSortedByRemainingRatio() {
        return dataStore.getBeers().stream()
                .sorted(Comparator.comparingDouble(this::getRemainIngredientRatio).reversed())
                .toList();
    }

    public void WriteOutRemainingRatioForBeers(){
        Consumer<Beer> beerConsumer = beer -> System.out.println(beer.getName() + " Water ratio:" + getRemainIngredientRatio(beer));
        listBeersSortedByRemainingRatio().forEach(beerConsumer);
    }

    private double getRemainIngredientRatio(Beer beer) {
        return 1-beer.getIngredients().stream()
                .mapToDouble(Ingredient::getRatio)
                .sum();
    }

}
