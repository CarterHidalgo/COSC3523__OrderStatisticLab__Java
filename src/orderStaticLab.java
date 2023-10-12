/*
### COSC3523__OrderStatisticLab__Java

Author(s): Carter Hidalgo  
Course: COSC3523 Analysis of Algorithms  
Project: COSC3523__OrderStatisticLab__Java  
Date Due: 10/17/2023  

Input: Generated random integers and a random ith order statistic  
Output: Number of comparisons for RandomSelect and Median-Of-Five on the same input  
Assumptions: N/A  
Limitations: Performance is measured only in number of comparisons  
Description: The program generates a random input and tests how quickly RandomSelect and Median-Of-Five can find the ith order statistic  

Development Computer:  LG Gram 2022  
Operating System: Windows 11 Home  
Compiler: Java Programming Language Compiler
Integrated Development Environment: Visual Studio Code  
Operational Status: Operational  
*/
import java.util.Arrays;

class orderStaticLab {
    public static void main(String[] args) {
        int inputSize = 1_000_000;
        int maxValue = Integer.MAX_VALUE;
        int target = (int) (Math.random() * inputSize);
        int arr[] = new int[inputSize];

        generateInput(arr, maxValue, arr.length);

        int[] sorted = Arrays.copyOfRange(arr, 0, arr.length);
        Arrays.sort(sorted);
        
        randomizedPartition rand = new randomizedPartition();
        
        int ith = rand.randomSelect(arr, 0, arr.length-1, target);

        if(ith == sorted[target-1]) {
            System.out.println("Found the " + (target-1) + "th smallest term in " + rand.getComparisons() + " comparisons");
        } else {
            System.out.println("Error in finding the " + target + "th smallest term");
        }
    }

    private static void generateInput(int[] arr, int max, int num) {
        for(int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * max);
        }
    }
}
