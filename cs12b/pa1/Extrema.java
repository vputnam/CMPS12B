//-----------------------------------------------------------------------------
// Extrema.java
// This program uses a recursive algorithm to return the minimum and maximum values in a subarray A[p...r]. If p = r that element is returned sice it is the only element in the array. If p < r a middle index q is computed and the max or min of sub arrays A[p...q] and A[(q+1)...r] are computed recursively.  
//-----------------------------------------------------------------------------

class Extrema {
   
     // maxArray()
     //returns the largest value in int array A
         static int maxArray(int[] A, int p, int r){
            int q = (p+r)/2;
    
            if(p==r){
               return A[p];
            } else {
               return compareMax(maxArray(A, p, q), maxArray(A, q+1, r));
            }
         }
                  
      // minArray()
      // returns the smallest value in int array A
         static int minArray(int[] A, int p, int r){
            int q = (p+r)/2;

            if(p==r){
               return A[p];
            } else {
               return compareMin(minArray(A, p, q), minArray(A, q+1, r));
            }
          }

         
      // compareMax()
      // compares two numbers and returns the max of the two 
         static int compareMax(int a, int b){
            if(a>b){
               return a;
            } else {
               return b;
            }
         }

      // compareMin()
      // compares two numbers and returns the min of the two 
         static int compareMin(int a, int b){
            if(a<b){
               return a;
            } else {
               return b;
            }
         }

      // main() 
         public static void main(String[] args) {
            int[] B = {-1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7};
	    System.out.println( "max = " + maxArray(B, 0, B.length-1) );  // output: max = 11 
	    System.out.println( "min = " + minArray(B, 0, B.length-1) );  // output: min = -3                                                        
         }
                                              
}


