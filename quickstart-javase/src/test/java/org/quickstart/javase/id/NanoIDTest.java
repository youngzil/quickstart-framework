package org.quickstart.javase.id;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

public class NanoIDTest {

    @Test
    public void testStandardIDs (){
        String id = NanoIdUtils.randomNanoId(); // "ku-qLNv1wDmIS5_EcT3j7"
        System.out.println("id=" + id);

    }

    @Test
    public void testCustomIDs (){

        // Use a faster, but non-secure, random generator
        Random random = new Random();

        // Use a custom alphabet containing only a, b, and c
        char[] alphabet = {'a','b','c'};

        // Make IDs 10 characters long
        int size = 10;

        String id = NanoIdUtils.randomNanoId(random, alphabet, size); // "babbcaabcb"

        System.out.println("id=" + id);
    }

}

