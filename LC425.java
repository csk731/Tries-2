// TC: O(N^L * L)
// SC: O(N*L + L^2)
// where N = number of words, L = length of each word
// Approach: Trie + Backtracking

import java.util.*;

public class LC425 {

    class Trie {
        Trie childs[];
        List<String> list;

        Trie() {
            childs = new Trie[26];
            list = new ArrayList<>();
        }
    }

    Trie trie;
    List<List<String>> ans;
    
    private void insertToTrie(String word){
        int i=0, n = word.length();
        Trie temp = trie;
        while(i<n){
            char ch = word.charAt(i);
            Trie nxt = temp.childs[ch-'a'];
            if(nxt == null) temp.childs[ch-'a'] = new Trie();
            temp = temp.childs[ch-'a'];
            temp.list.add(word);
            i++;
        }
    }

    private List<String> startsWith(String prefix){
        int n = prefix.length();
        int i=0;
        Trie temp = trie;
        while(i<n){
            char ch = prefix.charAt(i);
            Trie nxt = temp.childs[ch-'a'];
            if(nxt==null) return new ArrayList<>();
            temp = nxt;
            i++;
        }
        return temp.list;
    }

    private void backtrack(String[] words, int i, int k, int n, List<String> list){
        // BC
        if(i==k){
            ans.add(new ArrayList<>(list));
            return;
        }
        // Logic
        StringBuffer prefix = new StringBuffer();
        for(String w: list){
            prefix.append(w.charAt(i));
        }
        List<String> l = startsWith(prefix.toString());
        for(int x=0;x<l.size();x++){
            list.add(l.get(x));
            backtrack(words, i+1, k, n, list);
            list.remove(list.size()-1);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        int n = words.length;
        int k = words[0].length();
        trie = new Trie();
        for(int i=0;i<n;i++){
            trie.list.add(words[i]);
            insertToTrie(words[i]);
        }
        ans = new ArrayList<>();
        backtrack(words, 0, k, n, new ArrayList<>());
        return ans;
    }
}