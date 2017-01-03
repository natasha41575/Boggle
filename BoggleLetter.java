public class BoggleLetter {
 
   private boolean isVowel;
   private int r;
   private int c;
   private char letter;
   private int grid_size;
   
   public BoggleLetter(char L, int size){
      r = -1;
      c = -1;
      grid_size = size;
      letter = L;
      isVowel = checkVowel(L);
   }
   private static boolean checkVowel(char L){
      return (L == 'A' || L == 'E' || L == 'I' || L == 'O' || L == 'U');
   }
   public char getLetter(){
      return this.letter;
   }
   public int getRow(){
      return r;
   }
   public int getCol(){
      return c;
   }
   public boolean getIsVowel(){
      return this.isVowel;
   }
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