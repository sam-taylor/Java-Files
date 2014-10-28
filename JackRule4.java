import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author   Sam Taylor <samuel.taylor@yale.edu>
 * @version  4.0
 * @since  2011-10-11
 * This is a program that takes a sudoku grid in a text file and fills in the missing
 * spaces based on an algorithm.
 */

public class JackRule4
{
 
  
  public static void main(String[] args) throws FileNotFoundException
  {
    File f = new File("samplesudoku2.txt");
    Scanner s = new Scanner(f);
    
    
    //vals is an array of arrays, the following code initializes it
    int[][] vals;
    vals = new int[9][9];
    
    for (int row = 0; row < 9; row++)
    {
      for (int column = 0; column < 9; column++)
      {
        if (s.hasNextInt())
        {
          vals[row][column] = s.nextInt();
        }
      }
    }
    
    
    //Keep on checking rows until we stop solving squares
    boolean didSomething = true;
    while (didSomething)
    {
      didSomething = false;
      for (int i = 0; i < 9; i++)
      {
        didSomething = didSomething || checkRow(vals, i);
      }
    }
    
    //print out the multi-dimenstional array
    for (int row = 0; row < 9; row++)
    {
      for (int column = 0; column < 9; column++)
      {
        System.out.print(vals[row][column]+ " ");
      }
      System.out.println();
    }
  }
  
  public static boolean checkRow(int[][] vals, int row)
  {
    boolean retVal = false;
    boolean[] seenIt;
    seenIt = new boolean[9];
    
    //figure out what numbers are missing from the row
    for (int i = 0; i < vals[row].length; i++)
    {
      if (vals[row][i] != 0)
      {
        seenIt[vals[row][i]-1] = true;
      }
    }
    
    //now we know what's missing, suggest those missing numbers as candidates for the empty squares in the row
    for (int num = 1; num <=9; num++)
    {
      if (!seenIt[num-1])
      {
        int count = 0;
        int col = 0;
        for (int i = 0; i < 9; i++)
        {
          if (vals[row][i] == 0 && isPossible(vals, row, i, num))
          {
            count++;
            col = i;
          }
        }
        if (count == 1)
        {
          vals[row][col] = num;
          retVal = true;
        }
      }
    }
    
    return retVal;
  }
  
  //This method returns true if it is possible for numToCheck to be instide vals[row][column]
  public static boolean isPossible(int[][] vals, int row, int column, int numToCheck)
  {
    boolean isPossible = true;
    
    //check the column
    for (int i = 0; i < 9; i++)
    {
      if (vals[i][column] == numToCheck)
      {
        isPossible = false;
      }
    }
    
    //check the row
    for (int i = 0; i < 9; i++)
    {
      if (vals[row][i] == numToCheck)
      {
        isPossible = false;
      }
    }
    
    //check the 3x3 square
    for (int i = (row/3)*3; i < (row/3)*3 + 3; i++)
    {
      for (int j = (column/3)*3; j < (column/3)*3 + 3; j++)
      {
        if (vals[i][j] == numToCheck)
        {
          isPossible = false;
        }
      }
    }
    
    return isPossible;
  }
  
}