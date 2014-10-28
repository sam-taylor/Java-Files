import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author   Sam Taylor <samuel.taylor@yale.edu>
 * @version  1.0
 * @since  2011-10-11
 * This is a program that takes a text file, in this case the Lincoln Gettysburg Address, and prints it backwords, 
 * word for word. 
 */

public class LincolnReverse
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File f = new File("lincolnaddress.txt");
    Scanner s = new Scanner(f);
    
    String[] input;
    input = new String[300];
    
    int count = 0;
    while(s.hasNext())
    {
      String nextWord = s.next();
      input[count] = nextWord;
      count++;
    }
    
    for (int i = count-1; i >=0; i--)
    {
      System.out.print(input[i] + " ");
    }
    
  }
}