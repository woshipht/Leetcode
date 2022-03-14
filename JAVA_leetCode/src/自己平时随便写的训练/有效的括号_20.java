package 自己平时随便写的训练;

import java.util.HashMap;
import java.util.Stack;

public class 有效的括号_20 {
    public static void main(String[] args){
        String nums = "()";
        System.out.println(isValid(nums));
    }

    //使用stack栈数据结构，先入后出结构判断,如果是"({["就放，如果是")}]"就读，如果不一样就false

//    static boolean isValid(String s) {
//        Stack<Character> stack = new Stack<Character>();
//
//        if(s == null || s.length() == 0){
//            return true;
//        }
//
//        if(s.length()%2 == 1){
//            return false;
//        }else {
//            for(int i=0; i<s.length(); i++){
//                if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
//                    stack.push(s.charAt(i));
//                }else if(s.charAt(i) == ')'){
//                    if(!stack.empty() && stack.peek()=='('){
//                        stack.pop();
//                    }else {
//                        return false;
//                    }
//                }else if(s.charAt(i) == '}'){
//                    if(!stack.empty() && stack.peek()=='{'){
//                        stack.pop();
//                    }else {
//                        return false;
//                    }
//                }else if(s.charAt(i) == ']'){
//                    if(!stack.empty() && stack.peek()=='['){
//                        stack.pop();
//                    }else {
//                        return false;
//                    }
//                }
//            }
//        }
//        return stack.empty();
//    }

    //同上，但是使用hashmap和stack
    static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        HashMap<Character,Character> hashMap = new HashMap<Character,Character>(){{
            put(')','(');
            put('}','{');
            put(']','[');
        }};

        if(s == null || s.length() == 0){
            return true;
        }

        if(s.length()%2 == 1){
            return false;
        }else {
            for (int i = 0; i < s.length(); i++) {
                if (hashMap.containsKey(s.charAt(i))) {
                    if (stack.empty() || stack.peek() != hashMap.get(s.charAt(i))) {
                        return false;
                    }
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        return stack.empty();
    }
}
