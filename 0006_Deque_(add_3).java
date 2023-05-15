    public static boolean checkStringToPalindrome(String s){
        Deque deque = new Deque<>();
        for (int i = 0; i < s.length(); i++) {
            deque.addTail(s.charAt(i));
        }
        while (deque.size()>0){
            Object first = deque.removeFront();
            Object last = deque.removeTail();
            if (!first.equals(last)){
                return false;
            }
        }
        return true;
    }
