import java.io.*;
import java.util.*;

/**
 * @author   Sam Taylor <samuel.taylor@yale.edu>
 * @version  2.0
 * @since  2011-10-11
 * This is a program that takes a maze written in a text file and computes the solution, tallying the number of solver
 * steps and actual steps taken. The final version is more complete but is missing. 
 */

public class PS7v2 {
  public static void main (String[] args) throws FileNotFoundException {
    Scanner console = new Scanner(System.in);
    System.out.println("Which maze do you want to solve?");
    String f = console.nextLine();
    Scanner input = new Scanner(new File(f));
    System.out.println();
    System.out.println("Regular (1) or Competition (2):");
    //solveMaze2 works for some of the mazes but not all, in this version both options lead to solveMaze1
    int mode = console.nextInt();
    int[][][] maze = makeMaze(input);
    printMaze(maze);
    System.out.println();
    if (mode==1){
      solveMaze1(maze);
    }else if (mode==2){
      solveMaze1(maze);
    }
  }
  
  public static int[][][] makeMaze(Scanner input) {
    int rows = input.nextInt();
    int columns = input.nextInt();
    int[][][] maze = new int[rows][columns][12]; 
    int i = 0;
    input.nextLine();
    while (input.hasNextLine()){
      String row = input.nextLine();
      for (int j = 0; j < columns; j++){
        maze[i][j][0] = (int) row.charAt(j) - 48;
      }
      i++;
    }
    return maze;
  }
  
  public static void solveMaze1(int[][][] maze){
    int ys = -1;
    int xs = -1;
    int sstepcount = 0;
    int wstepcount = -1;
    Random rando = new Random();
    for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if (maze[i][j][0] == 2){
          ys = i;
          xs = j;
        }
      }
    }
    while (maze[ys][xs][0] != 3) {
      int d = rando.nextInt(4);
      if (d == 0 && maze[ys-1][xs][0] != 0){
        ys--;
        sstepcount ++;
      }else if (d == 1 && maze[ys+1][xs][0] != 0){
        ys++;
        sstepcount ++;
      }else if (d == 2 && maze[ys][xs-1][0] != 0){
        xs--;
        sstepcount ++;
      }else if (d == 3 && maze[ys][xs+1][0] != 0){
        xs++;
        sstepcount ++;
      }
      pruner(maze,ys,xs);
      if (sstepcount>0){
      maze[ys][xs][1] = 1;
      maze[ys][xs][2] = sstepcount;
      }
    }
    for(int i = 0;i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if (maze[i][j][1] == 1){
          wstepcount++;
        }
      }
    }
    System.out.println();
    System.out.println("Solution:");
    System.out.println("Solver steps: \t" + sstepcount + "\t Walking steps: \t" + wstepcount);
    printMaze(maze);
  }
  
  public static void solveMaze2(int[][][] maze){
    int ys = -1;
    int xs = -1;
    int sstepcount = 0;
    int wstepcount = 0;
    int branchcount = 1;
    int antistep = 0;
    int u = 0;
    int p = 0;
    int randotry = 0;
    Random rando = new Random();
    //find the starting point
    for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if (maze[i][j][0] == 2){
          ys = i;
          xs = j;
        }
      }
    }
    //perform random movements
    while (maze[ys][xs][0] != 3) {
      int d = rando.nextInt(4);
      if(maze[ys][xs][1]==0){
        if(maze[ys-1][xs][0] != 0){
          maze[ys][xs][5]++;  //at ys,xs, player can move to up.
        }
        if(maze[ys+1][xs][0] != 0){
          maze[ys][xs][5]++; //player can move down
        }
        if(maze[ys][xs-1][0] != 0){
          maze[ys][xs][5]++; //player can move left
        }
        if(maze[ys+1][xs+1][0] != 0){
          maze[ys][xs][5]++; //player can move right
        }
      }
      if(maze[ys][xs][5]>=3 && maze[ys][xs][1] == 0){
        branchcount++;
        maze[ys][xs][10]=branchcount;
      }else if (maze[ys][xs][5]<3 && maze[ys][xs][10]==branchcount){
        branchcount--;
        maze[ys][xs][10]=-1;
      }
      if (d == 0 && maze[ys-1][xs][0] != 0 && maze[ys][xs][4] != 1){
        ys--;
        sstepcount ++;
        maze[ys][xs][4] = d;
      }else if (d == 1 && maze[ys+1][xs][0] != 0 && maze[ys][xs][4] != 0){
        ys++;
        sstepcount ++;
        maze[ys][xs][4] = d;
      }else if (d == 2 && maze[ys][xs-1][0] != 0 && maze[ys][xs][4] != 3){
        xs--;
        sstepcount ++;
        maze[ys][xs][4] = d;
      }else if (d == 3 && maze[ys][xs+1][0] != 0 && maze[ys][xs][4] != 2){
        xs++;
        sstepcount ++;
        maze[ys][xs][4] = d;
      }else
      {
        randotry++;
      }  
      if (branchcount>=1){
      if (maze[ys][xs][1] > 0 || randotry>300){
        for(int n = 0;n < maze.length; n++){
          for(int o = 0; o < maze[n].length; o++){
            if(maze[n][o][10]==branchcount){
              ys = n;
              xs = o;
              antistep++;
            }
          }
        }
        for(int l = 0;l < maze.length; l++){
          for(int m = 0; m < maze[l].length; m++){
            if (maze[l][m][2] > maze[ys][xs][2]){
              maze[l][m][2] = 0;
              maze[l][m][1] = 0;  
            }
          }
        }
      }
      }else if (randotry>300 && branchcount<1){
            for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if (maze[i][j][0] == 2){
          ys = i;
          xs = j;
        }
      }
    }
      }
        
      maze[ys][xs][1] = 1;
      maze[ys][xs][2] = sstepcount; 
    }
    for(int i = 0;i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if (maze[i][j][1] == 1){
          wstepcount++;
        }
      }
    }
    System.out.println();
    System.out.println("Solution:");
    System.out.println("Solver steps: \t" + sstepcount + "\t Walking steps: \t" + (wstepcount - antistep));
    printMaze(maze);
  }
  
  public static void pruner(int[][][] maze, int ys, int xs){
    for(int i = 0;i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if ((ys == i && xs == j && maze[i][j][1] > 0)||maze[ys][xs][0]==2){
          for(int l = 0;l < maze.length; l++){
            for(int m = 0; m < maze[l].length; m++){
              if (maze[l][m][2] > maze[i][j][2]){
                maze[l][m][2] = 0;
                maze[l][m][1] = 0;
              }
            }
          }
        }
      }
    }
  }
  
  public static void printMaze(int[][][] maze){
    for(int i = 0;i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        if (maze[i][j][0] == 0){
          System.out.print("+");
        }else if (maze[i][j][0] == 1){
          if (maze[i][j][1] == 1){
            System.out.print(".");
          }else{
            System.out.print(" ");
          }
        }else if (maze[i][j][0] == 2){
          System.out.print("@");
        }else{
          System.out.print("#");
        }        
      }System.out.println();
    }
  }
}


