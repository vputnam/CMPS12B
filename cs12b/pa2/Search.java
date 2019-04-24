
import java.io.*;
import java.util.Scanner;


public class Search {

   // mergeSort()
   // sort subarray A[p...r]
   public static void mergeSort(String[] A, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         //int [] x = new int[A.length];
         //int [] y = new int[A.length]; 
         mergeSort(A, lineNumber, p, q);
         mergeSort(A, lineNumber, q+1, r);
         merge(A, lineNumber, p, q, r);
      }
   }

   // merge()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static void merge(String[] A, int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] x = new int[n1];
      int[] y = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++) L[i] = A[p+i];
      for(i=0; i<n1; i++) x[i] = lineNumber[p+i];
      for(j=0; j<n2; j++) R[j] = A[q+j+1];
      for(j=0; j<n2; j++) y[j] = lineNumber[q+j+1];
      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j]) < 0 ){
               A[k] = L[i];
               lineNumber[k] = x[i];
               i++;
               // System.out.println(x[i]);
            }else{
               A[k] = R[j];
               lineNumber[k] = y[j];
               j++;
               // System.out.println(y[j]);
            }
         }else if( i<n1 ){
            A[k] = L[i];
            lineNumber[k] = x[i]; 
            i++;
            // System.out.print(x[i]);
         }else{ // j<n2
            A[k] = R[j];
            lineNumber[k] = y[j]; 
            j++;
            // System.out.print(y[j]);
         }
      }
   }

   // binarySearch()
   // pre: Array A[p...r] is sorted 
   public static int binarySearch(String[] A, int p, int r,  String target){
      
      int q;
      if(p > r) {  
         return -1;
      }else{
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         if(target.compareTo(A[q]) == 0){
            return q;

         }else if(target.compareTo(A[q]) < 0){
            return binarySearch(A, p, q-1, target);
         }else{ // target > A[q]
            return binarySearch(A, q+1, r, target);
         }
      }
   }
   
   // line counts the file specified 
   public static void main(String[] args) throws IOException {

      // check number of command line arguments
      // print usage if less than 2 
      if(args.length < 2){
         System.err.println("Usage: Search file1 target1 [target2 ..]");
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

      Scanner sn = new Scanner(new File(args[0]));
      String[] S = new String[lineCount];
      int[] lineNumber = new int[lineCount];

      for(int i=0; i < lineCount; i++){
         S[i] = sn.nextLine();
      }

      for(int i=0; i < lineCount; i++){
         lineNumber[i] = i+1;
      //   System.out.println(lineNumber[i]);
       }

      mergeSort(S, lineNumber, 0, lineCount-1);

      for(int i=1; i < args.length; i++){
         int  q = binarySearch(S, 0, lineCount-1, args[i]);
         if(q != -1){
            // System.out.println(q);
            System.out.println(args[i]+" found on line "+lineNumber[q]);
         }else{
            System.out.println(args[i]+" not found");
      }
}
      

   }

}      
