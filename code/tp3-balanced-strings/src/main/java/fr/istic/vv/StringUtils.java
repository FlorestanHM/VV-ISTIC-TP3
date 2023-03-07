package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        // Create an empty stack
        Stack<Character> stack = new Stack<Character>();

        // Loop through each character in the input string
        for (char c : str.toCharArray()) {
            // If the character is an opening symbol, push it onto the stack
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }
            // If the character is a closing symbol, pop the top symbol from the stack
            // and check if it matches the closing symbol
            else if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == '}' && top != '{') || (c == ']' && top != '[') || (c == ')' && top != '(')) {
                    return false;
                }
            }
        }

        // If the stack is empty, the string is balanced
        return stack.isEmpty();
    }

}
