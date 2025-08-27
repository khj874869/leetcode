class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int remain, int start,
                           List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 같은 깊이에서의 중복 조합 방지
            if (i > start && nums[i] == nums[i - 1]) continue;
            // 남은 타겟을 초과하면 이후도 모두 초과(정렬 가정)
            if (nums[i] > remain) break;

            path.add(nums[i]);
            // i+1: 같은 원소 재사용 금지
            backtrack(nums, remain - nums[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
    }
