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
