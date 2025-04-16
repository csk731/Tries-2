import java.util.*;


// Approach 1: HashMap & PQ
// TC: O(nlogk)
// SC: O(n + k)

public class LC347 {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int key : map.keySet()) {
            pq.add(new Integer[] { key, map.get(key) });
            if (pq.size() > k)
                pq.poll();
        }
        int ans[] = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}

// Approach 2: Bucket Sort
// TC: O(n + m + k)
// SC: O(n + m + k)
// where m = max frequency of any number

class LC347_1 {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            max = Math.max(max, map.get(nums[i]));
        }
        List<Integer>[] list = new ArrayList[max + 1];
        for (int k1 : map.keySet()) {
            int f = map.get(k1);
            if (list[f] == null) {
                list[f] = new ArrayList<>();
            }
            list[f].add(k1);
        }
        int ans[] = new int[k];
        int ptr = 0;
        for (int i = max; i > 0; i--) {
            if (list[i] == null)
                continue;
            for (int j = 0; j < list[i].size() && ptr < k; j++) {
                ans[ptr++] = list[i].get(j);
            }
            if (ptr >= k)
                break;
        }
        return ans;
    }
}

// Approach 3: Quick Select
// TC: O(n)
// SC: O(n)

class LC347_2 {

    private List<int[]> quickSelect(List<int[]> arr, int k) {
        if (k == 0)
            return new ArrayList<>();

        int pivotIdx = new Random().nextInt(arr.size());
        int pivot = arr.get(pivotIdx)[1];

        List<int[]> left = new ArrayList<>();
        List<int[]> mid = new ArrayList<>();
        List<int[]> right = new ArrayList<>();

        for (int[] num : arr) {
            if (num[1] > pivot)
                left.add(num);
            else if (num[1] == pivot)
                mid.add(num);
            else
                right.add(num);
        }

        List<int[]> ans = new ArrayList<>();

        if (k < left.size()) {
            return quickSelect(left, k);
        }
        ans.addAll(left);
        if (k < left.size() + mid.size()) {
            ans.addAll(quickSelect(mid, k - left.size()));
            return ans;
        }
        ans.addAll(mid);
        if (k < left.size() + mid.size() + right.size()) {
            ans.addAll(quickSelect(right, k - left.size() - mid.size()));
            return ans;
        }
        ans.addAll(right);
        return ans;
    }

    public int[] topKFrequent(int[] nums, int k) {
        List<int[]> arr = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            arr.add(new int[] { num, map.get(num) });
        }

        List<int[]> ans = quickSelect(arr, k);
        int res[] = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i)[0];
        }
        return res;
    }
}