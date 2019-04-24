public class probe{

public static void main(String[] args){
   int A[] = {34,25,79,56,6};
   probSeq(A);

 }

public static void probSeq(int[] H){
   int table = 11;
   int probe, k, i;
   for(k=0; k<H.length; k++){
      for(i=0; i<table; i++){
         probe = (((H[k]%11) + i*(1 + H[k]%10))%11);
         System.out.print(probe+" ");
      }
      System.out.println("");
   }
}
}
