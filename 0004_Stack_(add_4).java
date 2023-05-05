    public static boolean balance(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean check = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(1);
                check = false;
            }
            if (s.charAt(i) == ')') {
                stack.pop();
            }
            if (stack.size() == 0 && check){
                return  false;
            }
            if (stack.size() == 0){
                check = true;
            }
        }
        return stack.size() == 0;
    }
