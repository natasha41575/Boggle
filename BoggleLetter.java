/** A BoggleLetter object. The BoggleGrid is a two-dimensional array of these. */

public class BoggleLetter {
 
   /** Data members. */
   private boolean isVowel;
   private int r;
   private int c;
   private char letter;
   private int grid_size;
   
   /** Constructor */
   public BoggleLetter(char L, int size){
      r = -1;
      c = -1;
      grid_size = size;
      letter = L;
      isVowel = checkVowel(L);
   }
 
   /** Determines if the letter is a vowel or not. Called when constructed. */
   private static boolean checkVowel(char L){
      return (L == 'A' || L == 'E' || L == 'I' || L == 'O' || L == 'U');
   }
 
   /** Returns the character of the letter. */
   public char getLetter(){
      return this.letter;
   }
 
   /** Returns the row  and column of the letter's position. */
   public int getRow(){
      return r;
   }
   public int getCol(){
      return c;
   }
  
   /** Returns whether the letter is a vowel or not. */
   public boolean getIsVowel(){
      return this.isVowel;
   }
 
   /** Allows the position to be changed. */
   public boolean setPosition(int r, int c){
      if (r >= 0 && r < grid_size && c >= 0 && c < grid_size){
          this.r = r;
          this.c = c;
          return true;
      }
      else return false;
   }
 
   public String toString() {
      return "" + letter;
   } 
}
