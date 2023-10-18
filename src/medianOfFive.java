import java.util.ArrayList;

public class MedianOfFive {
    private int numCompare = 0;

    int findStatistic(int[] A, int p, int r, int i) {
        int x = select(A, p, r);
        int q = partition(A, p, r, x);
        int k = q - p + 1;

        if(i == k) {
            numCompare++;
            return A[q];
        } else if(i < k) {
            numCompare += 2;
            return findStatistic(A, p, q-1, i);
        } else {
            numCompare += 2;
            return findStatistic(A, q+1, r, i-k);
        }
    }
    
    int select(int[] A, int p, int r) {
        int len = r - p + 1;

        numCompare++;
        if(len == 1) {
            return A[p];
        }

        ArrayList<ArrayList<Integer>> arrFives = new ArrayList<>();
        int arrLen = (len / 5) + ((len % 5) > 0 ? 1 : 0 );
        int count = 0;
        int start = p;

        numCompare++;
        for(int index = 0; index < arrLen; index++) {
            ArrayList<Integer> temp = new ArrayList<>();
            int size = Math.min(5, len - count);

            numCompare++;
            for(int t = 0; t < size; t++) {
                temp.add(A[start++]);
                count++;
                numCompare++;
            }
            arrFives.add(temp);
            numCompare++;
        }

        int[] medians = findMedians(arrFives);

        return select(medians, 0, medians.length-1);
    }

    int[] findMedians(ArrayList<ArrayList<Integer>> arr) {
        int[] medians = new int[arr.size()];

        numCompare++;
        for(int i = 0; i < arr.size(); i++) {
            sort(arr.get(i));
            medians[i] = arr.get(i).get((arr.get(i).size()-1) / 2);
            numCompare++;
        }

        return medians;
    }

    void sort(ArrayList<Integer> arr) {
        numCompare++;
        for(int nextPos = 1; nextPos < arr.size(); nextPos++) {
            insert(arr, nextPos);
            numCompare++;
        }
    }
    
    void insert(ArrayList<Integer> arr, int nextPos) {
        int insValue = arr.get(nextPos);
        
        numCompare++;
        while(nextPos > 0 && insValue < arr.get(nextPos - 1)) {
            arr.set(nextPos, arr.get(nextPos - 1));
            nextPos--;
            numCompare++;
        }

        arr.set(nextPos, insValue);
    }

    int partition(int[] arr, int p, int r, int x) {
        int start = p;
        int end = r;
 
        numCompare++;
        for(int i = start; i <= end;) {
            if (arr[i] < x) {
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                start++;
                i++;
                numCompare++;
            } else if (arr[i] > x) {
                int temp = arr[end];
                arr[end] = arr[i];
                arr[i] = temp;
                end--;
                numCompare += 2;
            } else {
                i++;
                numCompare += 2;
            }
        }

        return start;
    }

    int getComparisons() {
        return numCompare;
    }

    void setComparisons(int value) {
        numCompare = value;
    }
}
