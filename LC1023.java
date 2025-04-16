// TC: O(n*m)
// SC: O(1)
// where n is the number of queries and m is the average length of the query

public class LC1023 {
    private boolean isOk(String w1, String w2){
        int l1 = w1.length();
        int l2 = w2.length();
        int i=0, j=0;
        while(i<l1){
            char ch1 = w1.charAt(i);
            if(j<l2 && ch1==w2.charAt(j)) j++;
            else if(ch1>='A' && ch1<='Z') return false;
            i++;
        }
        return j>=l2;
    }
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries==null || queries.length==0){
            return new ArrayList<>();
        }
        int n = queries.length;
        int m = pattern.length();
        List<Boolean> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            boolean res = isOk(queries[i], pattern);
            ans.add(res);
        }
        return ans;
    }
}