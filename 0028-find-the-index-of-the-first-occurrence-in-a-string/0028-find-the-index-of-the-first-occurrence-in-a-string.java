class Solution {
    public int strStr(String haystack, String needle) {
        int index = -1;
        char c = needle.charAt(0);

        for (int i = 0; i < haystack.length(); i++) {
            if (i + needle.length() <= haystack.length() && c == haystack.charAt(i)) {
                String cut = haystack.substring(i, i + needle.length());

                if (cut.equals(needle)) {
                    index = i;
                    break;
                } else {
                    continue;
                }
            }
        }
        return index;
    }
}