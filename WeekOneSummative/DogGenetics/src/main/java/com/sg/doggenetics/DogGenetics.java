/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.doggenetics;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author tedis
 */
public class DogGenetics {
    
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        Random randomGenerator = new Random();
        
        String[] arrayOfDogBreeds = {"St. Bernard", "Chihuahua", "Dramatic RedNosed Asian Pug", "Common Cur", "King Doberman"};
        
        // Just making the random percentage number to an int whole number and add each random number equal to 100.
        int breedPercentage = 0;
        int lastDog = 0;
        int remainingPercentage = 0;
        String nameOfDog = "";
        
        // Ask input from user
        System.out.print("What is your dog's name? ");
        nameOfDog = inputReader.nextLine();
        System.out.println("");
        
        System.out.println("Well then, I have this highly reliable report on " + nameOfDog + "'s prestigious background right here.");
        System.out.println("");
        
        // currentPercentage is 101, instead of 100 because random generator generates numbers from 0 - 99.
        int currentPercentage = 101;
        for (int i = 0; i < (arrayOfDogBreeds.length - 1); i++) {
            breedPercentage = randomGenerator.nextInt(currentPercentage);
            System.out.println(breedPercentage + "% " + arrayOfDogBreeds[i]);
            currentPercentage = currentPercentage - breedPercentage;
        }
        
        // lastDog variable holds the location of the last dog breed for print out.
        lastDog = (arrayOfDogBreeds.length - 1);
        // *currentPercentage - 1* Rmember that the currentPercentage is one number higher because the random generator generates numbers from 0.
        remainingPercentage = currentPercentage - 1;
        // I printed out the last dog here to get the remaining percentage left so all percentage numbers would add up to 100.
        System.out.println(remainingPercentage + "% " + arrayOfDogBreeds[lastDog]);
    }
    
}
