package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import Users.EncryptedPassword;

public class EncryptedPasswordTest {
    @Test
    void test() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        EncryptedPassword encryptedPassword = new EncryptedPassword("hello");
        assertEquals("aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d", encryptedPassword.getEncryptedPassword());
    }

    @Test
    void testVerify() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        EncryptedPassword encryptedPassword = new EncryptedPassword("hello");
        assertEquals(true, encryptedPassword.verify("hello"));
    }

}
