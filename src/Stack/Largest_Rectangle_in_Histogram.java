package src.Stack;

import java.util.Stack;

public class Largest_Rectangle_in_Histogram {
    public static int largestRectangleArea(int[] heights) {
        int ns[] = findNextSmaller(heights);
        int ps [] = findPreviousSmaller(heights);
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < heights.length ; i++){
            int h = heights[i];
            int w = ns[i] - ps[i] -1;
            max = Math.max(max, (h * w));
        }
        return max;
    }
    public static int[] findNextSmaller(int arr[]){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int res[] = new int[n];
        for(int i = n- 1; i>= 0 ; i--){
            while(!stack.isEmpty() && arr[stack.peek()]>= arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                res[i] = n;
            }else{
                res[i] = stack.peek();
            }
            stack.push(i);
        }
        return res;
    }
    public static int [] findPreviousSmaller(int arr[]){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        for(int i = 0 ; i< n ; i++){
            while(!stack.isEmpty() && arr[stack.peek()]>= arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                res[i] = -1;
            }
            else{
                res[i] = stack.peek();
            }
            stack.push(i);
        }
        return res;
    }

    public static int largestReactangleAreaOtherMethod(int [] heights){
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i <= n ; i++){
            int element = (i == n )? 0: heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > element){
                int h = heights[stack.pop()];
                int ps = (stack.isEmpty())?-1:stack.peek();
                int w = i -ps -1;
                max = Math.max(max,h*w);
            }
            stack.push(i);
        }
        return (max == Integer.MIN_VALUE)?0:max;

    }
    public static void main(String[] args) {
      int[]  heights = {2,1,5,6,2,3};
      int ans = largestRectangleArea(heights);
      int ans1 = largestReactangleAreaOtherMethod(heights);
        System.out.println("Largest Rectangle is area is : "+ans);
        System.out.println("Largest Rectangle is area is : "+ans1);
    }
}
