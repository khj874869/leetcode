class Solution {
    public boolean hasMatch(String s, String p) {
        int k = p.indexOf("*");
        
        String left = p.substring(0, k); 
        String right = p.substring(k + 1); 
        
        int leftIdx = s.indexOf(left);
        
        if (leftIdx == -1) {
            return false;
        }
        
        int rightIdx = s.indexOf(right, leftIdx + left.length());
        
        return rightIdx != -1;
    }
}