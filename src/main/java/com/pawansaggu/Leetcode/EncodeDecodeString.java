package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeDecodeString {
    public static void main(String args[]) {
        StringUtils s = new StringUtils();
        List<String> strs = new ArrayList<>(Arrays.asList("we","say",":","yes","!@#$%^&*()"));

        System.out.println(s.encode(strs));
        System.out.println(s.decode(s.encode(strs)));
    }
}

class StringUtils {
    public String encode(List<String> strs) {
        String encoded = "";

        for (int i=0; i<strs.size(); i++) {
            int length = strs.get(i).length();

            encoded += length + "#" +strs.get(i);
        }
    
        return encoded;
    }

    public List<String> decode(String str) {
        List<String> output = new ArrayList<>();
        String decoded = "";
        int ptr = 0;
        int length = str.length();

        while (ptr < length) {
            String wordLength = "";

            while (ptr < length && str.charAt(ptr) != '#') {
                wordLength += str.charAt(ptr);

                ptr++;
            }

            ptr++;
            int breakPoint = ptr + Integer.parseInt(wordLength);

            while (ptr < breakPoint) {
                decoded += str.charAt(ptr);
                ptr++;
            }

            output.add(decoded);

            decoded = "";
        }

        return output;
    }
}
