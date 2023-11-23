
class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        return finaleasysolution(heights,queries);
    }
    //bit mistake 
    public int[] meet(int[] arr, int[][] query) {
        int[] nextmax=nextgreatest(arr);
        System.out.println(Arrays.toString(nextmax));
        int[] ans=new int[query.length];
        for(int q=0;q<query.length;q++){
            int i=query[q][0];
            int j=query[q][1];
            
            if(arr[i]==arr[j]){
                ans[q]=j;
                continue;
            }
            if(i<j && arr[i]<arr[j]){
                ans[q]=j;
            }
            else if(i>j && arr[i]>arr[j]){
                ans[q]=i;
            }
            else{
                
                int check=Math.max(i,j);
                int checkmin=Math.min(i,j);
                if(nextmax[check]==-1 || nextmax[checkmin]==-1 ){
                    ans[q]=-1;
                    continue;
                }
            
                if(arr[nextmax[check]]>arr[checkmin]){
                    ans[q]=nextmax[check];
                }
                else if(arr[nextmax[checkmin]]>arr[check]){
                    ans[q]=nextmax[checkmin];
                }
                else{
                    ans[q]=-1;
                }
            }
        }
        return ans;
    }
    public int[] nextgreatest(int[] arr){
        int[] ans=new int[arr.length];
        Stack<Integer> stack=new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while(!stack.isEmpty() && arr[stack.peek()]<=arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                ans[i]=-1;
            }else{
                ans[i]=stack.peek();
            }
            stack.push(i);
        }
        return ans;
    }

    public int[] finaleasysolution(int[] heights, int[][] queries){
        int[] ans=new int[queries.length];
        int[] nextmax=nextgreatest(heights);
        System.out.println(Arrays.toString(nextmax));
        for(int q=0;q<queries.length;q++){
            ans[q]=finalans(queries[q][0],queries[q][1],heights,nextmax);
        }
        return ans;
    }
    public int finalans(int left,int right,int[] heights,int[] nextmax){
        if(left==right){
            return right;
        }

        int i=Math.min(left,right);
        int j=Math.max(left,right);

        if(heights[i]<heights[j]){
            return j;
        }
        while(nextmax[j]!=-1){
            j=nextmax[j];
            if(heights[j]>heights[i]){
                return j;
            }
        }
        return -1;
    }    
    
}