class Solution {
    public String longestPalindrome(String s) {
        String lps = "";
        
        for(int i = 0; i < s.length(); i++) {
            String oddMax = isPalindrome(s, i, i);
            lps = (lps.length() < oddMax.length())? oddMax : lps;
            String evenMax = isPalindrome(s, i, i + 1);
            lps = (lps.length() < evenMax.length())? evenMax : lps;
        }

        return lps;
    }

    /**
     * @param s         탐색할 문자열
     * @param left      탐색을 시작할 왼쪽 인덱스
     * @param right     탐색을 시작할 오른쪽 인덱스
     * @return
     */
    public String isPalindrome(String s, int left, int right) {
        while(left >= 0 && right < s.length()) {
            if(s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } 
            else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }
}