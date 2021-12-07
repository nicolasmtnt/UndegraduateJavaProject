package com.example;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import Users.Cart;
import Users.Customer;

public class CustomerTest {

    @Test
    void test(){
        Cart cart = new Cart();
        UUID uuid = UUID.randomUUID();
        cart.addProduct(uuid, 10);
        cart.addProduct(uuid, 12);
        cart.removeProduct(UUID.randomUUID());
    }

    @Test
    void rechercheMagazin() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Customer cu = new Customer("Nicolas","azerty");

    }
}
