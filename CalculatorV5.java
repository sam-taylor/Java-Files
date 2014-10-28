import java.util.Scanner;
import java.lang.Math;

/**
 * @author   Sam Taylor <samuel.taylor@yale.edu>
 * @version  5.0
 * @since  2011-10-11
 * This is a calculator that accepts user input.
 */

public class CalculatorV5 {
  
  public static void main(String[] args) 
  {
    Scanner s = new Scanner(System.in);
    String answer = "";
    int i = 1;
    
    while (i > 0)
    {
      System.out.println();
      System.out.println("What do you want me to calculate?");
      String raw = s.nextLine();
      
      if (raw.equalsIgnoreCase("quit"))    {
        i = 0;
        System.out.println("That was nice, we should do this again sometime!");
      } else if (raw.equalsIgnoreCase("help")) {
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Either input an expression to evaluate or input a command");
        System.out.println("");
        System.out.println("acceptable operators: + - * / %");
        System.out.println("");
        System.out.println("List of commands:");
        System.out.println("abs(expr) -- absolute value");
        System.out.println("sqrt(expr) -- square root");
        System.out.println("exp(expr) -- exponential");
        System.out.println("log(expr) -- natural logarithm");
        System.out.println("log10(expr) -- base 10 log");
        System.out.println("quit -- exit the application");
        System.out.println("help -- I think you figured this out already");
        System.out.println("");
        System.out.println("---------------------------------------------------------");
      } else if (raw.equals("")) {
        System.out.println("Empty input, please try again.");
      } else if (raw.indexOf("abs") == 0 || raw.indexOf("sqrt") == 0 || raw.indexOf("exp") == 0 || raw.indexOf("log") == 0 || raw.indexOf("log10") == 0){
        if (matchedParenthesis(raw))
        {
          answer = performCommand(raw);
          if(isDouble(answer))
          {
            System.out.println("The answer is: " + answer);
          } else
          {
            System.out.println(answer);
            System.out.println("Error: Non-real result");
          }
        } else {
          System.out.println("Error: Mismatched parenthesis");
        }
      } else if (raw.indexOf("+") > 0 || raw.indexOf("-") > 0 || raw.indexOf("*") > 0 || raw.indexOf("/") > 0 || raw.indexOf("%") > 0) {
        if (matchedParenthesis(raw))
        {
          answer = calculateParenthesis(raw);
          System.out.println("The answer is: " + answer);
        } else
        {
          System.out.println("Error: Mismatched parenthesis");
        }
      }
      else if (isDouble(calculate(raw))) {
        answer = calculateParenthesis(raw);
        System.out.println("The answer is: " + answer);
      } else
      {
        System.out.println("Unrecognized command. Type 'help' for a list of recognizable commands."); 
        //due to my isDouble method not recognizing scientific notation, large numbers without operators or commands will be
        //rejected as unrecognized commands.
      }
      if (answer.equals("192820240.0") || answer.equals("1.9282024E8")) 
        //My personal best for Dolphin Olympics 2. As per the comment above, this must be
        //reached through an operation or command for the easter egg to appear.
      {
        System.out.println("HIGH SCORE!");
      } else if (answer.equals("69.0")) {
        System.out.println("Look at you!");
      }
    }
  }
  
  public static boolean isDouble(String term)
  {
    boolean response = false;
    if (term.indexOf("E") > 0)
    {
      String beforeE = term.substring(0, term.indexOf("E"));
      String afterE = term.substring(term.indexOf("E") + 1);
      if (isNonScientificDouble(beforeE) && isNonScientificDouble(afterE))
      {
        return (response = true);
      } else
      {
        return response;
      }
    } else if (isNonScientificDouble(term))
    {
      return (response = true);
    } else
    {
      return response;
    }
  }
  
  public static boolean isNonScientificDouble(String term)
  {
    boolean response = false;
    int x = 0;
    int i;
    int j = 0;
    if (term.indexOf("-") == 0)
    {
      j = 1;
    }
    for (i = j; i < term.length(); i++)
    {
      if ((int) term.charAt(i) == 46)
      {
        x++;
      } else if ((int) term.charAt(i) <= 47 || (int) term.charAt(i) >= 58)
      {
        return response;
      }
    }
    if (x <= 1)
    {
      response = true;
    }
    return response; 
  }
  
  public static boolean isOperator(String term)
  {
    boolean response = false;
    if (term.length() > 1)
    {
      return response;
    } else if ((int) term.charAt(0) != 37 && (int) term.charAt(0) != 42 && (int) term.charAt(0) != 43 && (int) term.charAt(0) != 45 && (int) term.charAt(0) !=47)
    {
      return response;
    } else
    {
      response = true;
      return response;
    }
  }
  
  public static boolean matchedParenthesis(String expression)
  {
    boolean response = false;
    int x = 0;
    int y = 0;
    String temp1 = expression;
    String temp2 = expression;
    if (expression.indexOf(")") >= 0 && expression.indexOf("(") >= 0)
    {
      while (temp1.indexOf(")") >= 0)
      {
        x++;
        temp1 = temp1.substring(temp1.indexOf(")") + 1);
      }
      while (temp2.indexOf("(") >= 0)
      {
        y++;
        temp2 = temp2.substring(temp2.indexOf("(") + 1);
      }
      if (x == y)
      {
        response = true;
      }
    } else if (expression.indexOf(")") >= 0 && expression.indexOf("(") >= 0)
    {
      response = true;
    } else if (expression.indexOf(")") == -1 && expression.indexOf("(") == -1)
    { 
      response = true;
    }
    return response;
  }
  
  public static String performCommand(String raw)
  {
    String result = "";
    double retVal = 0;
    double expression2;
    String expression = new String(raw.substring(raw.indexOf("(") + 1, raw.length() - 1));
    if (isDouble(calculateParenthesis(expression)))
    {
      expression2 = Double.parseDouble(calculateParenthesis(expression));
    } else
    {
      return calculateParenthesis(expression);
    }
    
    if (raw.substring(0, raw.indexOf("(")).equalsIgnoreCase("abs")){
      retVal = Math.abs(expression2);
    }else if (raw.substring(0, raw.indexOf("(")).equalsIgnoreCase("sqrt")) {      
      retVal = Math.sqrt(expression2);
    }else if (raw.substring(0, raw.indexOf("(")).equalsIgnoreCase("exp")) {      
      retVal = Math.exp(expression2);
    }else if (raw.substring(0, raw.indexOf("(")).equalsIgnoreCase("log")) {      
      retVal = Math.log(expression2);
    }else if (raw.substring(0, raw.indexOf("(")).equalsIgnoreCase("log10")){      
      retVal = Math.log10(expression2);
    }
    result = "" + retVal;
    return result;
  }
  
  public static String calculateParenthesis(String oos)
  {
    String result = "";
    if (oos.indexOf(")") > 0)
    {
      String tocalc;
      String restbehind;
      String restinfront = "";
      while (oos.indexOf(")") >= 0)
      {
        tocalc = oos.substring(0, oos.indexOf(")"));
        restbehind = oos.substring(oos.indexOf(")") + 1);
        if (oos.indexOf("(") >= 0)
        {
          while (tocalc.indexOf("(") >= 0)
          {
            restinfront = restinfront + tocalc.substring(0, tocalc.indexOf("("));
            tocalc = tocalc.substring(tocalc.indexOf("(") + 1); //2 + lol
          }
          if (isDouble(calculate(tocalc)))
          {
            oos = restinfront + Double.parseDouble(calculate(tocalc)) + restbehind;
          } else
          {
            return calculate(tocalc);
          }
        } else
        {
          oos = oos.substring(0, oos.indexOf(")"));
        }
      }
      result = calculate(oos);
      return result;
    } 
    result = calculate(oos);
    return result;
  }
  
  public static String calculate(String expression)
  {
    expression = expression + " "; 
    int index;
    String result = "";
    double y1;
    double x1;
    String y = "";
    String o = "";
    double retVal;
    String remaining = expression.substring(expression.indexOf(" ") + 1);
    
    if (isDouble(expression.substring(0,expression.indexOf(" "))))
    {
      x1 = Double.parseDouble(expression.substring(0,expression.indexOf(" ")));
      retVal = x1;
    } else
    {
      result = "Error: '" + expression.substring(0,expression.indexOf(" ")) + "' is not a double.";
      return result;
    }
    
    for (index = remaining.indexOf(" "); index >=0; index = remaining.indexOf(" "))
    {
      
      if (isOperator(remaining.substring(0,remaining.indexOf(" "))))
      {
        o = remaining.substring(0,remaining.indexOf(" "));
      } else 
      {
        result = "Error: '" + remaining.substring(0,remaining.indexOf(" ")) + "' is not an operator.";
        return result;
      }
      remaining = remaining.substring(remaining.indexOf(" ") + 1);
      if (isDouble(remaining.substring(0, remaining.indexOf(" "))))
      {
        y = remaining.substring(0, remaining.indexOf(" "));
      } else
      {
        result = "Error: '" + remaining.substring(0, remaining.indexOf(" ")) + "' is not a double.";
        return result;
      }
      
      remaining = remaining.substring(remaining.indexOf(" ") + 1);
      y1 = Double.parseDouble(y);
      if (o.equals("+"))
      {
        retVal = x1 + y1;
      }
      else if (o.equals("-"))
      {
        retVal = x1 - y1;
      }
      else if (o.equals("*"))
      {
        retVal = x1 * y1;
      }
      else if (o.equals("/"))
      {
        if (y1 == 0)
        {
          System.out.println("Please don't try to divide by zero, it will break me.");
          retVal = 0
            ;
        }   
        else
        {
          retVal = x1 / y1;
        }
      }
      else 
      {
        retVal = x1 % y1;
      }
      x1 = retVal;
    }
    result = result + x1;
    return result;
  }
}




