package com.example.jokejavalib;

import java.util.Random;

public class JokeTeller {

    private static final String[] jokes = new String[]{
            "My computer suddenly started belting out \"Someone Like You.\" It's a Dell.",
            "I had a joke about Neymar, but it doesn't stand",
            "Q: What do you call a bear with no teeth? A: Gummybear",
            "Q: Where did the sheep go on vacation? A: The baaaahamas",
            "How do you stop squirrels playing football in the garden? \n" +
                    "Hide the ball, it drives them nuts."
};

   public static String getAJoke(){
       Random random = new Random();
    return jokes[random.nextInt(jokes.length)];
   }



}
