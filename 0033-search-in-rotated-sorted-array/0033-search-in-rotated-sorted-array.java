class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 1. 중간 위치에서 찾았으면 종료
            if (nums[mid] == target) {
                return mid;
            }

            // 2. 오른쪽 구간이 정렬되어 있는 경우
            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 3. 왼쪽 구간이 정렬되어 있는 경우
            else {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        // 탐색 범위가 없어질 때까지 찾지 못함
        return -1;
    }
}