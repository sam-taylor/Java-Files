import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintStream;

public class PigLatinTranslaterFromFile
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File f = new File("lincolnaddress.txt");
    Scanner s = new Scanner(f);
    PrintStream output = new PrintStream(new File("Translated.txt"));
    
    while (s.hasNext())
    {
      String word = s.next();
      output.print(translate(word) + " "); //this is the only difference when writing to a file instead of to the console.
    }
    System.out.println();
  }
  
  public static String translate(String word)
  {
    boolean hasPunct = false;
    char temp;
    if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',')
    {
      hasPunct = true;
      temp = word.charAt(word.length() - 1);
      word = word.substring(0, word.length() - 1);
    }
    boolean firstLetterCapital = false;
    char firstLetter = word.charAt(0);
    String retVal;
    if (firstLetter >= 65 && firstLetter <= 90)//this is only true if the first letter is a capitol
    {
      firstLetter = (char)(firstLetter + 32);
      retVal = word.substring(1) + firstLetter + "ay";
      retVal = (char)(retVal.charAt(0) - 32) + retVal.substring(1);
    } else {
      retVal = word.substring(1) + firstLetter + "ay";
    }
    if (hasPunct)
    {
      return retVal;
      
      //return word.substring(1) + word.charAt(0) + "ay";
    }
  }
}