import java.util.Scanner;

public class GameOfLife {
  public static void main(String args[]) {
    Scanner obj = new Scanner(System.in);

    int rows = 30;
    int cols = 100;
    int iters = 100;

    // Two copies of the grid so it can be updated after each iteration
    char[][] arr = new char[rows][cols];
    char[][] newGrid = new char[rows][cols];

    // Setting up both grids
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        arr[r][c] = '_';
        newGrid[r][c] = '_';
      }
    }

    System.out.println("Welcome to Conways Game of Life.");

    print(arr);

    String input = "";

    while (true) {
      System.out.println(
          "Enter the row and column number you want to create a cell in the format 'row# column#' Enter 'done' when you are finished");

      input = obj.nextLine();

      if (input.toLowerCase().equals("done")) {
        print(arr);
        break;
      }

      String[] coordinates = input.split(" ");

      int row;
      int col;

      // Checking for valid input, putting the coordinates into the grid
      if (coordinates.length == 2) {
        if (Integer.valueOf(coordinates[0]) != null && Integer.valueOf(coordinates[1]) != null) {
          row = Integer.valueOf(coordinates[0]);
          col = Integer.valueOf(coordinates[1]);

          if (row <= rows && rows >= 1 && col <= cols && col >= 1) {
            arr[row - 1][col - 1] = 'O';
          } else {
            System.out.println("Error");
          }

        } else {
          System.out.println("Error");
        }
      } else {
        System.out.println("Error");
      }

      print(arr);
    }

    for (int k = 0; k < iters; k++) {
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {

          int liveNeighbors = 0;

          int neighborRow;
          int neighborCol;

          // Counts neighbors
          for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
              neighborRow = i + r;
              neighborCol = j + c;

              // Checking for bounds, and if the neighbor being checked isn't the cell itself
              if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < cols) {
                if (r != 0 || c != 0) {
                  if (arr[neighborRow][neighborCol] == 'O') {
                    liveNeighbors++;
                  }
                }
              }
            }
          }

          // Rules of Game of Life being applied.
          if (arr[i][j] == '_') {
            if (liveNeighbors == 3) {
              newGrid[i][j] = 'O';
            } else {
              newGrid[i][j] = '_';
            }
          } else if (arr[i][j] == 'O') {
            if (liveNeighbors < 2 || liveNeighbors > 3) {
              newGrid[i][j] = '_';
            } else {
              newGrid[i][j] = 'O';
            }
          }
        }
      }

      // Copying the initial grid into the new one for the next iteration
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          arr[r][c] = newGrid[r][c];
          newGrid[r][c] = '_';
        }
      }
      print(arr);

      // Delay to make it look cool
      try {
        Thread.sleep(300);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }

  // Prints out the array being passed
  public static void print(char[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
}