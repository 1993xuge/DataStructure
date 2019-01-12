package xuge.q804;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public static void main(String[] args) {

    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...",
                "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Set<String> treeSet = new HashSet<>();
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                builder.append(codes[word.charAt(i) - 'a']);
            }
            treeSet.add(builder.toString());
        }
        return treeSet.size();
    }
}
