import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

/**
 * @author   Sam Taylor <samuel.taylor@yale.edu>
 * @version  1.0
 * @since  2011-10-11
 * This is a program that takes picks a random number and helps guide the user to guess that number with feedback.
 */

public class GuessingGameClass
  
{
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    
    //declare and initialize a random object that we're going to use to pick a random number
    Random r = new Random();
    
    //r.nextInt(100) picks a random number between 0 and 99, so we must add 1
    int numberToGuess = r.nextInt(100) + 1;
    
    //System.out.println(numberToGuess);
    System.out.println("I've chosen a number between 1 and 100. Guess");
    int prevGuess = -1;
    boolean awesome = false;
    
    //note the boolean zen below
    //while is exactly the same as a for loop, except that we can think of it as only the condition.
    while (!awesome && s.hasNextInt() == true) //! = "not", s.rhasNextInt() will test to see if the input is an integer. The order of the test conditions
                                               //is important.
    {
      int guess = s.nextInt(); //This line will cause crashes whenever the user inputs anything besides a number (without hasNextInt before)
      if (guess == numberToGuess || r.nextInt(5) == 0)//|| denotes "or". just as with &&, if the first condition is true java won't check the 2nd.
      {
        System.out.println("You win! We're awesome!");
        awesome = true;
      }
      else if (guess == 69)
      {
       System.out.println("Look at you!");
      }
      else
      {
        if (prevGuess == -1)
        {
          System.out.println("Guess again.");
        }
        else if (Math.abs(numberToGuess - guess) > Math.abs(numberToGuess - prevGuess))
        {
          System.out.println("Colder");
        }
        else if (Math.abs(numberToGuess - guess) < Math.abs(numberToGuess - prevGuess))
        {
          System.out.println("Warmer");
        }
        else
        {
        System.out.println("That didn't help you, guess again.");
        }
      }
      prevGuess = guess;
      }
    System.out.println("-Game Over-");
  }
  }