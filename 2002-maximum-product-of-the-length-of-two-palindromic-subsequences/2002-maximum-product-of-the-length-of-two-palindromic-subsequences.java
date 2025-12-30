import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxProduct(String s) {
        int n = s.length();
        char[] charArray = s.toCharArray();
        
        // Key: 팰린드롬인 부분 수열의 비트마스크
        // Value: 해당 부분 수열의 길이
        Map<Integer, Integer> palindromeMap = new HashMap<>();
        
        // 1. 모든 부분 수열(Mask)을 확인하여 팰린드롬인 경우만 저장
        int totalMasks = 1 << n; // 2^n
        for (int mask = 1; mask < totalMasks; mask++) {
            String subSeq = getSubsequence(charArray, mask, n);
            if (isPalindrome(subSeq)) {
                palindromeMap.put(mask, subSeq.length());
            }
        }
        
        // 2. 두 팰린드롬 마스크가 겹치지 않는지((m1 & m2) == 0) 확인 후 최댓값 갱신
        int maxProd = 0;
        for (Integer m1 : palindromeMap.keySet()) {
            for (Integer m2 : palindromeMap.keySet()) {
                // 두 부분 수열이 겹치지 않는 경우 (Disjoint)
                if ((m1 & m2) == 0) {
                    maxProd = Math.max(maxProd, palindromeMap.get(m1) * palindromeMap.get(m2));
                }
            }
        }
        
        return maxProd;
    }
    
    // 비트마스크에 해당하는 문자열 생성
    private String getSubsequence(char[] arr, int mask, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // i번째 비트가 켜져 있다면 해당 문자 추가
            if ((mask & (1 << i)) != 0) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
    
    // 팰린드롬 여부 확인
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}