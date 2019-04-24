public class sortWords{

public static void main(String[] args){
   String A[] = {"cat", "dog", "foot", "ice", "help"};
   sortWords(A);
   for(int i=0; i<A.length; i++)
      System.out.println(A[i]);

 }

public static void sortWords(String[] W){
   int i, j;
   String temp;
   for(j=1; j< W.length; j++){
      temp = W[j];
      i = j-1;
      while(i<=0 && temp.compareTo(W[i])<0){
         W[i+1] = W[i];
         i--;
      }
      W[i+1] = temp; 

   }
}  
}
