package linkedlist1;

public class JoinLists {
    public static LinkedList joinLists(LinkedList list1, LinkedList list2) {
        LinkedList result = new LinkedList();
        if (list1.count() != list2.count()){
            return result;
        }
        Node nodeFromFirst = list1.head;
        Node nodeFromSecond = list2.head;
        while (nodeFromFirst != null && nodeFromSecond != null) {
            result.addInTail(new Node(nodeFromFirst.value + nodeFromSecond.value));
            nodeFromFirst = nodeFromFirst.next;
            nodeFromSecond = nodeFromSecond.next;
        }
        return result;
    }
}
