/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author tedis
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        boolean isInvalid = true;
        double myDouble = 0.0;
        
        while (isInvalid) {
            try {
                myDouble = Double.parseDouble(userInput.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                System.out.println("You can't do that...");
                System.out.println(prompt);
            }
        }

        return myDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double myDouble = readDouble(prompt);

        /*
        while (myDouble < min || myDouble > max) {
            System.out.println("");
            System.out.println("Error...");
            System.out.println("Please give me a number from " + min + " and " + max);
            myDouble = readDouble(prompt);
        }
        */

        return myDouble;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        boolean isInvalid = true;
        float myFloat = 0.0F;
        
        while (isInvalid) {
            try {
                myFloat = Float.parseFloat(userInput.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                System.out.println("You can't do that...");
                System.out.println(prompt);
            }
        }

        return myFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float myFloat = readFloat(prompt);

        /*
        while (myFloat < min || myFloat > max) {
            System.out.println("");
            System.out.println("Error...");
            System.out.println("Please give me a number from " + min + " and " + max);
            myFloat = readFloat(prompt);
        }
        */

        return myFloat;
    }

    @Override
    public int readInt(String prompt) {
        System.out.print(prompt);
        boolean isInvalid = true;
        int myInt = 0;
        while (isInvalid) {
            try {
                myInt = Integer.parseInt(userInput.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Give me an integer value!");
                System.out.println("");
                System.out.print(prompt);
            }
        }
        return myInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int myInt = readInt(prompt);

        while (myInt < min || myInt > max) {
            System.out.print("ERROR: ");
            System.out.println("Please give me a number from " + min + " and " + max);
            myInt = readInt(prompt);
        }
        
        return myInt;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        boolean isInvalid = true;
        long myLong = 0L;

        while (isInvalid) {
            try {
                myLong = Long.parseLong(userInput.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                System.out.println("You can't do that...");
                System.out.println(prompt);
            }
        }
        
        return myLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long myLong = readLong(prompt);

        /*
        while (myLong < min || myLong > max) {
            System.out.println("");
            System.out.println("Error...");
            System.out.println("Please give me a number from " + min + " and " + max);
            myLong = readLong(prompt);
        }
        */

        return myLong;
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);

        String myString = userInput.nextLine();

        System.out.println("");
        return myString;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        BigDecimal myBigDecimal = new BigDecimal(0);
        boolean isInvalid = true;

        while (isInvalid) {
            try {
                myBigDecimal = new BigDecimal(userInput.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                System.out.println("You can't do that...");
                System.out.println(prompt);
            }
        }
        
        //BigDecimal myBigDecimal = userInput.nextBigDecimal();
        return myBigDecimal;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal myBigDecimal = readBigDecimal(prompt);
        
        // To compare BigDecimals use .compareTo method
        // For example: x.compareTo(y)
        // It will return 0 if x and y are equal,
        // 1 if x is greater y
        // -1 if x is less than y
        while ((myBigDecimal.compareTo(min) == -1) || (myBigDecimal.compareTo(max) == 1)) {
            System.out.println("");
            System.out.println("Error...");
            System.out.println("Please give me a number from " + min + " and " + max);
            myBigDecimal = readBigDecimal(prompt);
        }
        
        return myBigDecimal;
    }
}
