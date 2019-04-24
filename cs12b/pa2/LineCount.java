import java.io.*;
import java.util.Scanner;

public class LineCount {

   public static void main(String[] args) throws IOException {
      
      // check number of command line arguments
      if(args.length != 1){
         System.err.println("Usage: LineCount file");
         System.exit(1);
      }
      
      // count lines, words, and chars in file
      Scanner in = new Scanner(new File(args[0]));
      int lineCount = 0;
      while( in.hasNextLine() ){
         in.nextLine();
         lineCount++;
      }
      in.close();
      
      System.out.println( args[0]+" contains "+lineCount+" lines" );
      
   }
}
