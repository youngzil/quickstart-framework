package org.quickstart.code.leetcode.other.solution;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/12/3 20:57
 */
public class TwoSum {

    public static void twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    System.out.println("i=" + i + ",j=" + j);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15, 3, 7, 5, 6};
        int target = 9;
        twoSum(nums, target);
    }

}
