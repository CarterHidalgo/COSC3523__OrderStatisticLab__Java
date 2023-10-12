import java.util.Arrays;

class OrderStaticLab {
    public static void main(String[] args) {
        int startSize = 100_000;
        int endSize = 1_000_000;
        int stepSize = 100_000;

        runComparisons(startSize, endSize, stepSize); 
    }
    
    private static void generateInput(int[] arr, int max) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
    }

    private static void runComparisons(int start, int stop, int stepSize) {
        if(stop < start || start < 0 || stepSize <= 0) {
            System.out.println("Invalid arguments. Expected (start > 0, stop > start, stepSize > 0)");
            return;
        }

        int inputSize = start;
        int maxValue = Integer.MAX_VALUE;
        int arr[];
        int sorted[];
        int target;
        int randResults;
        int medianResults;
        RandomizedPartition rand = new RandomizedPartition();

        for(int i = start; i <= stop; i += stepSize) {
            arr = new int[inputSize];
            target = (int) ((Math.random() * (inputSize)) + 1);

            generateInput(arr, maxValue);

            sorted = Arrays.copyOfRange(arr, 0, arr.length);
            Arrays.sort(sorted);

            randResults = rand.randomSelect(arr, 0, arr.length-1, target);

            if(randResults != sorted[target-1]) {
                System.out.println("Error on size " + inputSize + "; " + randResults + " is not equal to " + sorted[target-1]);
                break;
            } else {
                System.out.println("Found the " + target + "th smallest in " + rand.getComparisons() + " comparisons");
            }

            inputSize += stepSize;
        }
    }
}
