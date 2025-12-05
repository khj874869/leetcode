class Solution {
   
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;
        int halfLen = (m + n + 1) / 2;

        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = halfLen - i;

            // 인덱스 에러를 방지하기 위해 MIN_VALUE, MAX_VALUE를 사용합니다.

            // L1: nums1의 왼쪽 마지막 원소
            int L1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            // R1: nums1의 오른쪽 첫 번째 원소
            int R1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            // L2: nums2의 왼쪽 마지막 원소
            int L2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            // R2: nums2의 오른쪽 첫 번째 원소
            int R2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 5. 올바른 분할 지점인지 확인 (L1 <= R2 && L2 <= R1)
            if (L1 <= R2 && L2 <= R1) {
                int maxLeft = Math.max(L1, L2);

                // 전체 길이가 홀수인 경우
                if ((m + n) % 2 != 0) {
                    return maxLeft;
                } 
                // 전체 길이가 짝수인 경우
                else {
                    int minRight = Math.min(R1, R2);
                    return (maxLeft + minRight) / 2.0;
                }
            } 
            else if (L1 > R2) {
                high = i - 1;
            } else { // L2 > R1
                low = i + 1;
            }
        }
        
        return 0.0;
    }
}