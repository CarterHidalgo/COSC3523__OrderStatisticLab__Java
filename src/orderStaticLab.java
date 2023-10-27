import java.util.Arrays;

class OrderStaticLab {
    public static void main(String[] args) {
        int start = 100_000;
        int stop = 1_000_000;
        int step = 100_000;

        runComparisons(start, stop, step); 
    }
    
    private static void generateInput(int[] arr, int max) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
    }

    private static void runComparisons(int start, int stop, int step) {
        if(stop < start || start <= 0 || step <= 0) {
            System.out.println("Invalid arguments. Expected (start > 0, stop > start, step > 0)");
            return;
        }

        int numRuns = ((stop - start) / step) + 1;
        int inputSize = start;
        int maxValue = Integer.MAX_VALUE;
        int arr[];
        int sorted[];
        int randCopy[];
        int medianCopy[];
        int target;
        int expected;
        int randResults;
        int medianResults;
        int[][] comparisons = new int[numRuns][2];
        int index = 0;

        RandomizedPartition rand = new RandomizedPartition();
        MedianOfFive median = new MedianOfFive();

        for(int i = start; i <= stop; i += step) {
            arr = new int[inputSize];
            target = (int) ((Math.random() * (inputSize)) + 1);

            generateInput(arr, maxValue);

            sorted = Arrays.copyOfRange(arr, 0, arr.length);
            randCopy = Arrays.copyOfRange(arr, 0, arr.length);
            medianCopy = Arrays.copyOfRange(arr, 0, arr.length);
            Arrays.sort(sorted);
            expected = sorted[target-1];
                            
            rand.setComparisons(0);
            randResults = rand.randomSelect(randCopy, 0, randCopy.length-1, target);
            
            median.setComparisons(0);
            medianResults = median.findStatistic(medianCopy, 0, medianCopy.length-1, target);

            if(randResults != expected) {
                System.out.println("Error in randomPartition on size " + inputSize + "; " + randResults + " is not equal to " + expected);
                break;
            } else 
            if(medianResults != expected) {
                System.out.println("Error in medianOfFive on size " + inputSize + "; " + medianResults + " is not equal to " + expected);
                break;
            } else {
                comparisons[index][0] = rand.getComparisons();
                comparisons[index][1] = median.getComparisons();

                index++;
            }

            inputSize += step;
        }

        System.out.println("randomPartitioning:\n");
        for(int i = 0; i < comparisons.length; i++) {
            System.out.println(comparisons[i][0]);
        }
        
        System.out.println("\nmedianOfFive:\n");
        for(int i = 0; i < comparisons.length; i++) {
            System.out.println(comparisons[i][1]);
        }
    }
}
