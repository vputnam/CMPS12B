#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

void sortWords(char** W, int n){
   int i, j;
   char* temp;
   for(j=1;j<n;j++){
      W[i]=temp;
      i=j-1;
      while(i>=0 && strcmp(temp,W[i])<0){
         W[i+1]=W[i];
         i--;
      }
   }
   W[i+1]=W[i];
}

char main(int argc, char** argv){
   char* A[] = {"a","p","p","l","e"};
   int n = i;
   sortWords(A,n);
   int i;
   for(i=0; i<n; i++)
      printf(A[i]);


return(EXIT_SUCCESS);
}
