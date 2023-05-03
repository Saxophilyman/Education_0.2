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
            } else if (stack.peek().equals("+")) {
                stack.pop();
                x = stack2.pop() + stack2.pop();
                stack2.push(x);
            } else if (stack.peek().equals("*")) {
                stack.pop();
                x = stack2.pop() * stack2.pop();
                stack2.push(x);
            } else if (stack.peek().equals("-")) {
                stack.pop();
                x = stack2.pop();
                x = stack2.pop() - x;
                stack2.push(x);
            } else if (stack.peek().equals("/")) {
                stack.pop();
                x = stack2.pop();
                try {
                    x = stack2.pop() / x;
                } catch (ArithmeticException e) {
                    System.err.println("Нельзя делить на ноль");
                }
//                x = stack2.pop() / x;
                stack2.push(x);
            } else if (stack.peek().equals("=")) {
                stack.pop();
                return stack2.pop();
            }
        }
        return Integer.parseInt(null);
    }
