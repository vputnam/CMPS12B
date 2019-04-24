//package com.java2novice.sorting;
 
public class MyQuickSort{
     
    private int array[];
    private int length;
 
    public void sort(int[] inputArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
         for(int k:array){
             System.out.print(array[k]);
             System.out.print(" ");
           }
        System.out.println(" ");
        quickSort(0, length - 1);
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
         for(int k:array){
             System.out.print(array[k]);
             System.out.print(" ");
           }
        System.out.println(" ");
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        System.out.println(" ");
        while (i <= j) {
         for(int k:array){
             System.out.print(array[k]);
             System.out.print(" ");
           }
        System.out.println(" ");

            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
    for(int k:array){
             System.out.print(array[k]);
             System.out.print(" ");
           }
        System.out.println(" ");
                i++;
            }
            while (array[j] > pivot) {
 for(int k:array){
             System.out.print(array[k]);
             System.out.print(" ");
           }
        System.out.println(" ");

                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //for(int k:array){
                //   System.out.print(array[k]);
                //   System.out.print(" ");
                //}
                //System.out.println(" ");
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);

    }
 
    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        for(int k:array){
             System.out.print(array[k]);
             System.out.print(" ");
           }
        System.out.println(" ");
    }
     
    public static void main(String a[]){
         
        MyQuickSort sorter = new MyQuickSort();
        int[] input = {9,0,8,1,7,2,6,3,5,4};
        sorter.sort(input);
       // for(int i:input){
       //     System.out.print(i);
       //     System.out.print(" ");
       // }
    }
}


