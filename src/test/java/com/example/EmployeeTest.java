package com.example;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import Users.Employee;

public class EmployeeTest {
    @Test
    void test(){
        try {
            Employee em = new Employee("nicolas", "azerty",true);
            System.out.println(em);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
    }

}
