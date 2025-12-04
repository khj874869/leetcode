import java.util.*;
class Solution {
    public String convert(String s, int numRows) {
         if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
         StringBuilder[] sb = new StringBuilder[numRows];
         for(int i = 0; i<numRows;i++){
            sb[i] = new StringBuilder();
         }
         boolean Tf = false;
         int currow = 0;
        for(char c : s.toCharArray()){
            sb[currow].append(c);
            if(currow ==0 || currow == numRows -1){
                Tf = !Tf;
            }
            currow += Tf? 1:-1;
        }
        
        StringBuilder sba = new StringBuilder();
        for(StringBuilder as : sb){
            sba.append(as);
        }
        return sba.toString();
       
    }
    

}