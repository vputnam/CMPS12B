#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

void printArray(int* A, int n){
   int i;
   for(i=0; i<n; i++){
      printf("%d ", A[i]);
   }
   printf("\n");
}

void swap(int* A, int i, int j){
   int temp;
   temp = A[i];
   A[i] = A[j];
   A[j] = temp;
}

void BubbleSort(int* A, int n){
   int i, j;
   for(j=n-1; j>0; j--){
      for(i=1; i<=j; i++){
         if( A[i]<A[i-1] ) swap(A, i, i-1);
      }
   }
}



int main(int argc, char** argv){
   int i, n=9;
   int A[] = {3,2,1,8,7,4,6,5,9};
   int B[9];

   printArray(A, n);

   BubbleSort(A, n);
   //SelectionSort(A, n);
   //InsertionSort(A, n);
   //MergeSort(A, 0, n-1);
  // QuickSort(A, 0, n-1);
   //CountingSort(A, B, n, 9);

    printArray(A, n);


   return(EXIT_SUCCESS);
}
