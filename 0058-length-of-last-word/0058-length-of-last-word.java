class Solution {
    public int lengthOfLastWord(String s) {
       int cnt = 0;
       String q = s.trim();
       for(int i = q.length()-1; i >= 0;i--){
        cnt++;
        if( q.charAt(i) == ' '){
           cnt =cnt-1;
           break;
        }
       }
       return cnt;
    }
}