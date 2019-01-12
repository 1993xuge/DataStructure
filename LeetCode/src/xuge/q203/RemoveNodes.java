package xuge.q203;

public class RemoveNodes {

    private ListNode solutionNotWithHead(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode listNode = head;
            head = head.next;
            listNode.next = null;
        }
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode node = prev.next;
                prev.next = node.next;
                node.next = null;
                // 切记，prev不需要往后挪
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    private ListNode solutionWithHead(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode node = prev.next;
                prev.next = node.next;
                node.next = null;
                // 切记，prev不需要往后挪
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }



    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
