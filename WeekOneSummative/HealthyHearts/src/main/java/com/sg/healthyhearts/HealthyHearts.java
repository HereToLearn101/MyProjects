/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author tedis
 */
public class HealthyHearts {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        int age = 0;
        double bpmLevelOne = 0;
        double bpmLevelTwo = 0;
        double percentLevelOne = 0.50;
        double percentLevelTwo = 0.85;

        System.out.print("What is your age? ");
        age = inputReader.nextInt();

        bpmLevelOne = (220 - age) * percentLevelOne;
        bpmLevelTwo = (220 - age) * percentLevelTwo;

        System.out.println("You maximum heart rate should be " + (220 - age) + " beats per minute");
        System.out.println("Your target HR Zone is " + Math.round(bpmLevelOne) + " - " + Math.round(bpmLevelTwo) + " beats per minute");
    }
}
