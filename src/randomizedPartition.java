public class randomizedPartition {
    int numCompare = 0;
    
    int randomSelect(int[] A, int p, int r, int i) {
        numCompare++;
        if(p == r) {
            return A[p];
        }

        int q = randomPartition(A, p, r);
        int k = q - p + 1;
        
        if(i == k) {
            numCompare++;
            return A[q];
        } else if(i < k) {
            numCompare += 2;
            return randomSelect(A, p, q-1, i);
        } else {
            numCompare += 2;
            return randomSelect(A, q+1, r, i-k);
        }
    }

    int randomPartition(int[] A, int p, int r) {
        int piv = (int) ((Math.random() * (r - p)) + p);
        
        int tmp = A[piv];
        A[piv] = A[r];
        A[r] = tmp;
        
        int x = A[r];
        int i = p - 1;
        
        numCompare++;
        for(int j = p; j < r; j++) {
            if(A[j] <= x) {
                i++;
                
                tmp = A[j];
                A[j] = A[i];
                A[i] = tmp;
            } 
            numCompare++;
        }

        tmp = A[r];
        A[r] = A[i+1];
        A[i+1] = tmp;

        return i + 1;
    }

    int getComparisons() {
        return numCompare;
    }
}
