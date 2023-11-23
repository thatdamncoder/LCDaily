package StacksandQueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NextSmallest {
    public static void main(String[] args) {
        int[] arr={3,0,0,2,0,4};
        int[] ans=maxleft(arr);
//        int[] ans=nextsmallest(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(maxright(arr)));
        System.out.println(watertrapping(arr));
    }
    static int[] nextgreatest(int[] arr){
       Stack<Integer> stack= new Stack<>();
       int[] ans = new int[arr.length];
       for(int i=arr.length-1;i>=0;i--){
           while(!stack.isEmpty() && stack.peek()<=arr[i]){
               stack.pop();
           }
           if(stack.isEmpty()) {
               ans[i] = -1;
           }
           else{
               ans[i]=stack.peek();
           }
           stack.push(arr[i]);
       }
       return ans;
    }
    static int[] nextsmallest(int[] arr){
        Stack<Integer> stack= new Stack<>();
        int[] ans = new int[arr.length];
        for(int i=arr.length-1; i>=0;i--){
            while(!stack.isEmpty() && stack.peek()>=arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()) {
                ans[i]=-1;
            }
            else{
                ans[i]=stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }
    static int[] countnextsmallest(int[] arr){
        Stack<Integer> stack1= new Stack<>();
        Stack<Integer> stack2= new Stack<>();
        int[] count = new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
          if(stack1.isEmpty() && stack2.isEmpty()){
              count[i]=0;
              stack1.push(arr[i]);
              continue;
          }
          if(!stack1.isEmpty()){
              while(!stack1.isEmpty()){
                  if(stack1.peek()<=arr[i]){
                      count[i]++;
                  }
                  stack2.push(stack1.pop());
              }
              stack2.push(arr[i]);
              continue;
          }

          if (!stack2.isEmpty()) {
              while (!stack2.isEmpty()) {
                  if (stack2.peek() <= arr[i]) {
                      count[i]++;
                  }
                  stack1.push(stack2.pop());
              }
              stack1.push(arr[i]);
          }
        }
        return count;
    }
    static int[] countnumberofnextsmallest(int[] arr){
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        int[] count=new int[arr.length];

        for(int i=arr.length-1;i>=0;i--){
            if(stack1.isEmpty() && stack2.isEmpty()){
                count[i]=0;
                stack2.push(arr[i]);
                continue;
            }

            while(!stack2.isEmpty()){
                if(stack2.peek()<arr[i]){
                    count[i]++;
                }
                stack1.push(stack2.pop());
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack2.push(arr[i]);
        }
        return count;
    }

    static int[] prevsmallest(int[] arr){
        Stack<Integer> stack= new Stack<>();
        int[] result=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && stack.peek()>=arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[i]=-1;
            }
            else{
                result[i]=stack.peek();
            }
            stack.push(arr[i]);
        }
        return result;
    }
    static int[] prevgreatest(int[] arr){
        Stack<Integer> stack= new Stack<>();
        int[] result=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && stack.peek()<=arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[i]=-1;
            }
            else{
                result[i]=stack.peek();
            }
            stack.push(arr[i]);
        }
        return result;
    }
    static int[] maxleft(int[] arr){
        Stack<Integer> stack= new Stack<>();
        int[] maxl=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            if(stack.isEmpty()){
                stack.push(arr[i]);
                maxl[i]=-1;
                continue;
            }
            if(arr[i]<stack.peek()){
                maxl[i]=stack.peek();
            }
            else if(arr[i]>=stack.peek()){
                maxl[i]=-1;
                stack.pop();
                stack.push(arr[i]);
            }
        }
        return maxl;
    }
    static int[] maxright(int[] arr){
        Stack<Integer> stack= new Stack<>();
        int[] maxr=new int[arr.length];

        for(int i=arr.length-1;i>=0;i--){
            if(stack.isEmpty()){
                stack.push(arr[i]);
                maxr[i]=-1;
                continue;
            }
            if(arr[i]<stack.peek()){
                maxr[i]=stack.peek();
            }
            else if(arr[i]>=stack.peek()){
                maxr[i]=-1;
                stack.pop();
                stack.push(arr[i]);
            }
        }
        return maxr;
    }
    static int watertrapping(int[] arr){
        int result=0;
        int[] l=maxleft(arr);
        int[] r=maxright(arr);
        for(int i=0;i<arr.length;i++){
            if(l[i]==-1 || r[i]==-1) continue;
            result=result+Math.min(l[i],r[i]) - arr[i];
        }
        return result;
    }

}
