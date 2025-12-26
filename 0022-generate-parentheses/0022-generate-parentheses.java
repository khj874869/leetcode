class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recursive(res, "(", n - 1, n);
        return res;
    }

    public void recursive(List<String> res, String s, int open, int close) {
        if (open == 0 && close == 0) {
            res.add(s);
            return;
        }

        if (open > 0) {
            recursive(res, s + "(", open - 1, close);
        }

        if (close > open) {
            recursive(res, s + ")", open, close - 1);
        }
    }
}