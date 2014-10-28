import java.util.Scanner;

/**
 * @author   Sam Taylor <samuel.taylor@yale.edu>
 * @version  1.0
 * @since  2011-10-11
 * This is a program that performs some simple calculations on the user's income. This was most relevant right after 
 * Obama was elected in 2008.
 */

public class ObamaTaxStrategy
{
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    System.out.println("How much money do you make?");
    double income = s.nextDouble();
    
    System.out.println("Your pre-tax income is: " + income);
    if (income > 250000)
    {
      System.out.println("Your taxes will go up.");
      System.out.println("Obama is not your friend");
      income = income * .6;
      System.out.println("At least you have a job");
    }
    else if (income >= 200000)
    {
      System.out.println("Your taxes will stay the same.");
      income = income * .65;
      System.out.println("At least you have a job");
    }
    else if (income >= 0)
    {
      System.out.println("Your taxes will go down.");
      System.out.println("Obama is your friend.");
      income = income * .8;
      if (income != 0)
      {
        System.out.println("At least you have a job");
      }
      else
      {
        System.out.println("You should have majored in Computer Science");
      }
    }
    System.out.println("Your post-tax income will be: " + income);
    System.out.println("Are you happy with this proposal?");
    String ans = s.next();
    if (ans.equals("yes") && income > 250000)
    {
      System.out.println("You are either Warren Buffet or a member of Hollywood. Either way can I have your autograph?");
    }
  }
}