package com.example.jokejavalib;

import java.util.ArrayList;
import java.util.Random;

public class JokeTeller {

    private static String[] jokes = new String[]{
            "Joke de mon oncle",
            "Joke de ma tante",
            "Joke du grand-pere",
            "Joke de la voisine",
            "Joke du patron"};

   public static String getAJoke(){
       Random random = new Random();
    return jokes[random.nextInt(jokes.length)];
   }



}
