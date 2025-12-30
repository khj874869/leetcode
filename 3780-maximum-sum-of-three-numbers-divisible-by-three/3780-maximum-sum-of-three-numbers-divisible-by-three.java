import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int maximumSum(int[] nums) {
        List<Integer> r0 = new ArrayList<>();
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();

        for (int num : nums) {
            if (num % 3 == 0) r0.add(num);
            else if (num % 3 == 1) r1.add(num);
            else r2.add(num);
        }

        Collections.sort(r0, Collections.reverseOrder());
        Collections.sort(r1, Collections.reverseOrder());
        Collections.sort(r2, Collections.reverseOrder());

        int maxSum = 0;

        
        if (r0.size() >= 3) {
            int sum = r0.get(0) + r0.get(1) + r0.get(2);
            maxSum = Math.max(maxSum, sum);
        }

        if (r1.size() >= 3) {
            int sum = r1.get(0) + r1.get(1) + r1.get(2);
            maxSum = Math.max(maxSum, sum);
        }

        if (r2.size() >= 3) {
            int sum = r2.get(0) + r2.get(1) + r2.get(2);
            maxSum = Math.max(maxSum, sum);
        }

        if (r0.size() >= 1 && r1.size() >= 1 && r2.size() >= 1) {
            int sum = r0.get(0) + r1.get(0) + r2.get(0);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum; 
    }
}