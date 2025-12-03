class Solution {
    public int reverse(int x) {
        int rev = 0; // 뒤집힌 결과를 저장할 변수

        while (x != 0) {
            int pop = x % 10;   // x의 마지막 자리 숫자
            x /= 10;            // 마지막 자리 제거

            // 오버플로우(overflow) 발생하기 직전에 미리 체크
            // 양수 쪽 체크
            if (rev > Integer.MAX_VALUE / 10 || 
               (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // 음수 쪽 체크
            if (rev < Integer.MIN_VALUE / 10 || 
               (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;  // 숫자를 한 자리 밀고(pop 추가)
        }

        return rev;
    }
}
