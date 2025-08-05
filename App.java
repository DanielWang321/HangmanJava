import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        // --- 1. SETUP ---
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] wordBank = {
            "avenue", "awkward", "banjo", "blizzard", "bookworm", "buzzing", "espionage",
            "faking", "fixable", "fluffy", "galaxy", "haphazard", "injury", "jackpot",
            "jigsaw", "jogging", "kiwi", "length", "luxury", "oxygen", "pixel", "puzzle",
            "quiz", "strength", "twelve", "unknown", "wave", "wizard", "youth", "zipper"
        };

        boolean playAgain = true;
        int wins = 0;

        System.out.println("Welcome to Hangman, Alexander!");

        // --- 2. MAIN GAME LOOP (for multiple rounds) ---
        while (playAgain) {
            // --- 2a. INITIALIZE NEW ROUND ---
            String secretWord = wordBank[random.nextInt(wordBank.length)];
            char[] secretWordChars = secretWord.toCharArray();
            char[] playerGuess = new char[secretWord.length()];
            Arrays.fill(playerGuess, '_'); // Fills the array with underscores

            int turns = 8;
            boolean wordIsGuessed = false;
            String guessedLetters = ""; // To keep track of letters already guessed

            System.out.println("\n---------------------------------");
            System.out.println("A new word has been chosen!");
            System.out.println("Your word has " + secretWord.length() + " letters.");

            // --- 3. GUESSING LOOP (for a single word) ---
            while (turns > 0 && !wordIsGuessed) {
                System.out.println("\nYou have " + turns + " turns left.");
                printCurrentState(playerGuess);

                // --- 3a. GET USER INPUT ---
                System.out.print("Guess a letter: ");
                String input = scanner.next().toLowerCase();
                char guess = input.charAt(0); // Take the first character of the input

                // --- 3b. VALIDATE AND PROCESS GUESS ---
                if (guessedLetters.indexOf(guess) != -1) {
                    System.out.println("You already guessed that letter! Try another.");
                    continue; // Skip the rest of the loop and ask for another guess
                }
                
                guessedLetters += guess; // Add the new guess to our list
                boolean isGuessCorrect = false;

                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWordChars[i] == guess) {
                        playerGuess[i] = guess;
                        isGuessCorrect = true;
                    }
                }

                // --- 3c. PROVIDE FEEDBACK ---
                if (isGuessCorrect) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect!");
                    turns--; // Only decrement turns on an incorrect guess
                }
                
                // Check if the word has been completely guessed
                if (Arrays.equals(secretWordChars, playerGuess)) {
                    wordIsGuessed = true;
                }
            }

            // --- 4. END OF ROUND ---
            System.out.println("\n---------------------------------");
            if (wordIsGuessed) {
                System.out.println("Congratulations, you win! You guessed the word:");
                System.out.println(secretWord);
                wins++;
            } else {
                System.out.println("You ran out of turns! Game Over.");
                System.out.println("The word was: " + secretWord);
            }

            System.out.println("Total wins: " + wins);

            // --- 5. ASK TO PLAY AGAIN ---
            System.out.print("\nWould you like to play again? (y/n): ");
            String choice = scanner.next().toLowerCase();
            if (!choice.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\nThanks for playing! Have a great day!");
        scanner.close(); // Good practice to close the scanner
    }

    /**
     * Helper method to print the current state of the player's guess.
     * Example output: _ a _ _ l _
     * @param array The character array representing the player's progress.
     */
    public static void printCurrentState(char[] array) {
        System.out.print("Current word: ");
        for (char c : array) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}