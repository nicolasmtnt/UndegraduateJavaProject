package com.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Item.Items;
import Item.Movie;

/**
 * Unit test for simple App.
 */
class ItemsTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testProductCategory() {
        Movie m = new Movie("Star Wars", "1977", "George Lucas", "Lucasfilm");
        assertEquals("Movie", m.getClassName());
    }

    @Test
    void ItemsAdding() throws IOException {
        Movie m = new Movie("Star Wars", "1977", "George Lucas", "Lucasfilm");

        Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
        assertEquals(1, Items.map.size());

        Items.add("videogame", new String[]{"Super Mario bros","2019","Nintendo","Nintendo"});
        assertEquals(2, Items.map.size());

        Items.add("movie", new String[]{"Star Wars","1977","George Lucas","Lucasfilm"});

        Items.display();

        assertEquals(m.toString(), Items.getValue(Items.getUUID("movie", "Star Wars", "1977")).toString());
        assertEquals("Album,Dark Side of the Moon,1988,Pink Floyd,Universal", Items.getValue(Items.getUUID("album", "Dark Side of the Moon", "1988")).toString());

        Items.map.remove(Items.getUUID("album", "Dark Side of the Moon", "1988"));
        assertEquals(2, Items.map.size());
        Items.display();
        
    }
    
}
