    public static int postfix(String s) {
        String[] symbols = s.split(" ");
        Stack<String> stack = new Stack<String>();
        Stack<Integer> stack2 = new Stack<Integer>();
        for (int i = symbols.length - 1; i >= 0; i--) {
            stack.push(symbols[i]);
        }

        while (stack.size() > 0) {
            int x = Integer.MIN_VALUE;
            try {
                x = Integer.parseInt(stack.peek());
            } catch (NumberFormatException e) {
            }
            if (x != Integer.MIN_VALUE) {
                stack2.push(Integer.valueOf(stack.pop()));
                continue;
            } else
                x = stack2.pop();
            if (stack.peek().equals("+")) {
                x += stack2.pop();
            } else if (stack.peek().equals("*")) {
                x *= stack2.pop();
            } else if (stack.peek().equals("-")) {
                x = stack2.pop() - x;
            } else if (stack.peek().equals("/")) {
                try {
                    x = stack2.pop() / x;
                } catch (ArithmeticException e) {
                    return 0000000;
                }
            } else if (stack.peek().equals("=")) {
                return x;
            }
            stack.pop();
            stack2.push(x);
        }
        return 0000000;
    }
