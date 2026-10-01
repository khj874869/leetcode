class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                int duplicateValue = cur.val;

                // 같은 값을 가진 노드를 모두 지나간다.
                while (cur != null && cur.val == duplicateValue) {
                    cur = cur.next;
                }

                // 이전 노드를 중복 구간 다음 노드와 연결한다.
                prev.next = cur;
            } else {
                // 중복이 없는 노드는 남긴다.
                prev = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}