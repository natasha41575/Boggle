/** A BoggleGrid object that fills itself with random letters when constructed.
  * Follows certain rules such as:
  * 	the letter Q is always found next to a U
  *     no letter may take up more than 20% of the grid
  *     a vowel is twice as likely to show up as a consonant.
  */

import java.util.Random;

public class BoggleGrid {
  
  /** data members */
  private BoggleLetter[][] grid;
  private int size;
  private char[] letters = new char[31]; //vowels show up twice
  private int[] letterCounts = new int[31];
  
  /** constructor */
  public BoggleGrid(int size) {
    grid = new BoggleLetter[size][size];
    this.size = size;
    setLetters();
    fillGrid();
  }
  
  /** Creates an array of letters, where vowels show up twice. */
  private void setLetters() { 
    letters[0] = 'A';
    for (int i = 1; i < letters.length; i++) {
      letters[i] = (char) (letters[i - 1] + 1);
    }
    letters[26] = 'A';
    letters[27] = 'E';
    letters[28] = 'I';
    letters[29] = 'O';
    letters[30] = 'U';
  }

  /** Fills the grid with random letters. */
  private void fillGrid() {
    Random rand = new Random();
    int position;

    for (int r = 0; r < size; r++) 
      for (int c = 0; c < size; c++) {

        char L;
        do {
          L = getRandomLetter();
        } while (tooMany(L));

        grid[r][c] = new BoggleLetter(L, size);
        grid[r][c].setPosition(r, c);

	      addCount(grid[r][c].getLetter());

        if (L == 'Q')
          addU(r, c, rand);          
      }
  }
  
  /** Retrieves a random letter from the array of letters. */
  private char getRandomLetter() {
    Random r = new Random();
    int index = r.nextInt(31);
    return letters[index];
  }

  /** Called whenever a Q is put on the grid.
    * Adds the letter U to a random position next to the Q.
    */
  private void addU(int r, int c, Random rand){
    BoggleLetter current_letter = new BoggleLetter('U', size);
    
    //each number from 0-8 corresponds to a position around the letter Q.
    int position = rand.nextInt(8);   
    switch (position){
      case 0:
         if (current_letter.setPosition(r, c-1)){
            grid[r][c-1] = current_letter;
            break;
         }
      case 1:
         if (current_letter.setPosition(r-1, c-1)){
            grid[r-1][c-1] = current_letter;
            break;
         }
      case 2:
         if (current_letter.setPosition(r-1, c)){
            grid[r-1][c] = current_letter;
            break;
         }
      case 3:
         if (current_letter.setPosition(r-1, c+1)){
            grid[r-1][c+1] = current_letter;
            break;
         }
      case 4:
         if (current_letter.setPosition(r, c+1)){
            grid[r][c+1] = current_letter;
            break;
         }
      case 5:
         if (current_letter.setPosition(r+1, c+1)){
            grid[r+1][c+1] = current_letter;
            break;
         }  
      case 7:
         if (current_letter.setPosition(r, c+1)){
            grid[r-1][c+1] = current_letter;
            break;
         }  
      case 8:
         if (current_letter.setPosition(r+1, c-1)){
            grid[r+1][c-1] = current_letter;
            break;
         }                     
    }
  }
  
  /** Keeps track of how many of each letter there are in the grid. */
  private void addCount(char letter) {
    int index = -1;
    for (int i = 0; i < letters.length; i++) {
      if (letter == letters[i]) {
        index = i;
        break;
      }
    }
    letterCounts[index]++;
  }
  
  /** Checks if there are over 20% of the same letter in the grid. */
  private boolean tooMany(char L) {
    long threshold = Math.round(size * size * 0.2);
    int index = -1;
    for (int i = 0; i < letters.length; i++) {
      if (letters[i] == L) {
        index = i; //find where the letter occurs
        break;
      }
    }
    return letterCounts[index] > threshold;
  } 
	
  /** Returns the BoggleLetter at the given position. */
  public BoggleLetter getLetterAtPosition(int r, int c){
    return this.grid[r][c];
  } 
	
  public String toString() { 
    String temp = "";
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        temp = temp + grid[r][c].getLetter() + " ";
      }
      temp = temp + "\n";
    }
    return temp;
  }
 
}
