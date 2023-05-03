    public static boolean balance(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int index = 0;
        do {
            if (s.charAt(index) == '(') {
                stack.push(1);
            }
            if (s.charAt(index) == ')') {
                stack.pop();
            }
            index++;
        }
        while (stack.size() != 0 && index < s.length());
        return stack.size() == 0 && index == s.length();
    }
