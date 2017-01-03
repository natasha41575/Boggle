import java.util.Random;

public class BoggleGrid {
  
  private BoggleLetter[][] grid;
  private int size;
  private char[] letters = new char[31]; //vowels show up twice
  private int[] letterCounts = new int[31];
  
  public BoggleGrid(int size) {
    grid = new BoggleLetter[size][size];
    this.size = size;
    setLetters();
    fillGrid();
  }
  
  private void setLetters() { //add a for loop to fill letters array
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
  
  private char getRandomLetter() {
    Random r = new Random();
    int index = r.nextInt(31);
    return letters[index];
  }

  private void addU(int r, int c, Random rand){
    BoggleLetter current_letter = new BoggleLetter('U', size);

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
  
  private boolean tooMany(char L) { //checks if there are over 20% of the same letter in the grid
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

  public BoggleLetter getLetterAtPosition(int r, int c){
    return this.grid[r][c];
  }  
}