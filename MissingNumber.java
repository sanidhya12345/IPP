import java.util.*;
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        HashMap<Integer,Boolean> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],true);
        }
        for(int i=0;i<nums.length+1;i++){
            if(!map.containsKey(i)){
                return i;
            }
        }
        return nums.length;
    }
    public static void main(String[] args) {
      int [] nums={3,0,1};
      System.out.println(missingNumber(nums));
    }
}