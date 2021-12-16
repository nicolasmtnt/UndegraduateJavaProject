package com.example;

import org.junit.jupiter.api.Test;

import Gestionnary.Stock;
import Item.Items;

public class AppTest {
    @Test
    void test(){

    Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
    Items.add("videogame", new String[]{"Super Mario bros","2019","Nintendo","Nintendo"});
    Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
    Items.display();

    Stock.add("movie", "Star Wars", "2010", 50);
    Stock.add("videogame", "Super Mario bros", "2019", 12);
    
    Stock.display();

    Stock.toMarketplace("movie", "Star Wars", "2010", 20, 19.99);
    Stock.toMarketplace("videogame", "Super Mario bros", "2019", 2, 19.99);

    Stock.display();
    }
}
