class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // i 중복 스킵
            int lo = i + 1, hi = n - 1;

            while (lo < hi) {
                long sum = (long) nums[i] + nums[lo] + nums[hi]; // 오버플로우 방지
                if (sum < 0) {
                    lo++;
                } else if (sum > 0) {
                    hi--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    // lo/hi 중복 스킵
                    int loVal = nums[lo], hiVal = nums[hi];
                    while (lo < hi && nums[lo] == loVal) lo++;
                    while (lo < hi && nums[hi] == hiVal) hi--;
                }
            }
        }
        return res; 
    }
}