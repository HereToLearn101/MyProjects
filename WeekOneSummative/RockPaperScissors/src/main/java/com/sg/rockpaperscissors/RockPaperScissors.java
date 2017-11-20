/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author tedis
 */
public class RockPaperScissors {

    // made scanner static so it can be read by other methods
    static Scanner inputReader = new Scanner(System.in);
    static boolean continuePlaying = true;
    // when creating a boolean variable that has not been declared a value yet,
    // will have a default value of 'false'
    static boolean rightOption;

    public static void main(String[] args) {

        String userInputs;
        String changeCaseInput = "";

        do {
            playRockPaperScissors();
            if (continuePlaying) {
                rightOption = false;
                while (!(rightOption)) {
                    System.out.println("Do you want to play again? (Yes/No)");
                    userInputs = inputReader.nextLine();
                    changeCaseInput = userInputs.toLowerCase();
                    System.out.println("");
                    if ((changeCaseInput.equals("yes")) || (changeCaseInput.equals("no"))) {
                        rightOption = true;
                    } else {
                        System.out.println("Please say 'yes' or 'no'. Caps don't matter.");
                    }
                }
            }
        } while (changeCaseInput.equals("yes"));
    }

    static void playRockPaperScissors() {
        Random randomGenerator = new Random();

        int numberOfRounds = 0;
        int userChoice = 0;
        int computerChoice = 0;
        int userWins = 0;
        int computerWins = 0;
        int ties = 0;
        int index = 0;
        String changeCaseInput = "";

        boolean run = true;
        boolean checkNumberOfRounds = true;

        do {
            numberOfRounds = inputNumberOfRounds(numberOfRounds);           
            // Comment out the if and else part if you are trying the 'other way'.
            
            if (!((numberOfRounds >= 1) && (numberOfRounds <= 10))) {
                System.out.println("Please give me a number from 1 - 10.");
                checkNumberOfRounds = false;
            } else {
                checkNumberOfRounds = true;
            } 
        } while (!(checkNumberOfRounds));

        while ((continuePlaying) && (index < numberOfRounds)) {
            do {
                // Don't need to check if input is not any of these because it's already controlled by the default case
                System.out.println("Pick your choice? ");
                System.out.println("Choice '1': Rock");
                System.out.println("Choice '2': Paper");
                System.out.println("Choice '3': Scissors");
                System.out.println("Choice '4': Quit game");
                userChoice = inputReader.nextInt();

                System.out.println("");
                inputReader.nextLine();

                switch (userChoice) {
                    case 1:
                        computerChoice = randomGenerator.nextInt(3) + 1;
                        if (computerChoice == userChoice) {
                            System.out.println("You chose rock.");
                            System.out.println("Computer also chose rock.");
                            System.out.println("TIE");
                            System.out.println("");
                            ties++;
                        } else if (computerChoice == 3) {
                            System.out.println("You chose rock.");
                            System.out.println("Computer chose scissors.");
                            System.out.println("YOU WIN");
                            System.out.println("");
                            userWins++;
                        } else {
                            System.out.println("You chose rock.");
                            System.out.println("Computer chose paper.");
                            System.out.println("YOU LOSE");
                            System.out.println("");
                            computerWins++;
                        }
                        run = false;
                        index++;
                        break;
                    case 2:
                        computerChoice = randomGenerator.nextInt(3) + 1;
                        if (computerChoice == userChoice) {
                            System.out.println("You chose paper.");
                            System.out.println("Computer also chose paper.");
                            System.out.println("TIE");
                            System.out.println("");
                            ties++;
                        } else if (computerChoice == 1) {
                            System.out.println("You chose paper.");
                            System.out.println("Computer chose rock.");
                            System.out.println("YOU WIN");
                            System.out.println("");
                            userWins++;
                        } else {
                            System.out.println("You chose paper.");
                            System.out.println("Computer chose scissors.");
                            System.out.println("YOU LOSE");
                            System.out.println("");
                            computerWins++;
                        }
                        run = false;
                        index++;
                        break;
                    case 3:
                        computerChoice = randomGenerator.nextInt(3) + 1;
                        if (computerChoice == userChoice) {
                            System.out.println("You chose scissors.");
                            System.out.println("Computer also chose scissors.");
                            System.out.println("TIE");
                            System.out.println("");
                            ties++;
                        } else if (computerChoice == 2) {
                            System.out.println("You chose scissors.");
                            System.out.println("Computer chose paper.");
                            System.out.println("YOU WIN");
                            System.out.println("");
                            userWins++;
                        } else {
                            System.out.println("You chose scissors.");
                            System.out.println("Computer chose rock.");
                            System.out.println("YOU LOSE");
                            System.out.println("");
                            computerWins++;
                        }
                        run = false;
                        index++;
                        break;
                    case 4:
                        rightOption = false;
                        // ***Note*** putting a boolean variable inside a loop like displayed below
                        // will have a default value of 'true' regardless of what value you initialized
                        // it with outside this loop.
                        while (!(rightOption)) {
                            System.out.println("Are you sure? (yes/no)");
                            String answer = inputReader.nextLine();
                            changeCaseInput = answer.toLowerCase();
                            System.out.println("");
                            if (changeCaseInput.equals("yes")) {
                                continuePlaying = false;
                                run = false;
                                rightOption = true;
                            } else if (changeCaseInput.equals("no")) {
                                System.out.println("");
                                System.out.println("Continue....");
                                continuePlaying = true;
                                rightOption = true;
                            } else {
                                System.out.println("Please say 'yes' or 'no'. Caps don't matter. ");
                            }
                        }
                        break;
                    default:
                        System.out.println("");
                        System.out.println("Please choose right option!");
                }
            } while (run);
        }

        if (continuePlaying) {
            System.out.println("");
            System.out.println("----------------MATCH END----------------------");
            System.out.println("Results: ");
            System.out.println("User wins: " + userWins);
            System.out.println("Computer wins: " + computerWins);
            System.out.println("Ties: " + ties);
            System.out.println("");

            if (userWins > computerWins) {
                System.out.println("You beat the computer!");
            } else if (userWins < computerWins) {
                System.out.println("Computer has beaten you!");
            } else {
                System.out.println("No winner...");
            }
            System.out.println("");
        }
    }

    static int inputNumberOfRounds(int numberOfRounds) {
        
        
        System.out.println("How many rounds you want to play? ");
        numberOfRounds = inputReader.nextInt();
        System.out.println("");
        inputReader.nextLine();
        return numberOfRounds;
        
        // This part is to handle the data type error without exception handling
        // Uncomment this part if you want try this. If you do, don't forget to comment out the if and else part inside
        // the do-while loop after the part where inputNumberOfRounds method is called.
        
        /*
        boolean trueOrFalse = true;
        String stringNumber = "";
        String[] arrayOfOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        while (trueOrFalse) {
            System.out.println("How many rounds you want to play? ");
            stringNumber = inputReader.nextLine();
            for (int i = 0; i < arrayOfOptions.length; i++) {
                if (stringNumber.equals(arrayOfOptions[i])) {
                    numberOfRounds = Integer.parseInt(stringNumber);
                    trueOrFalse = false;
                    i = arrayOfOptions.length;
                }
            }
            if (trueOrFalse) {
                System.out.println("Please give me a number from 1 - 10!");
            }
        }
        return numberOfRounds;
*/
        
    }
}
