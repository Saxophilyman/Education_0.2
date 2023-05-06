    public static void moveRoundQueue(int n, Queue q) {
        if (n >= q.size()) {
            return;
        }
        for (int i = 0; i < n; i++) {
            q.enqueue(q.dequeue());
        }
    }
