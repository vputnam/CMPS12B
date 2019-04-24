#include<stdio.h>
#include<stdlib.h>

   int getValue(int a, int b, int n){
      int x, c;
      printf("arrive: a=%d b=%d\n", a, b);
      c = (a+b)/2;
      if( c*c <= n){
         x = c;
      }else{
         x = getValue(a, c-1, n);
      }
      printf("depart: a=%d b=%d\n ", a, b);
      return x;
    }
  
    void main(void){
       printf("%d",getValue(3,13,5));
    }
 
