import java.util.Scanner;

public class LinkedListReverseKGroups {

    // Reverse k nodes in the linked list
    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k <= 1) return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy, curr = head, next = null;

        // Count the total number of nodes in the list
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        curr = head; // Reset curr to head

        // Reverse in groups of k
        while (count > 0) {
            int groupSize = Math.min(k, count); // Handle last group (which may have < k nodes)
            if (groupSize == 1) break;         // No need to reverse a single node

            Node groupPrev = prev;
            Node groupEnd = curr;

            // Reverse the group
            for (int i = 0; i < groupSize; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // Connect the reversed group to the remaining list
            groupPrev.next = prev;
            groupEnd.next = curr;

            // Move prev to the end of the reversed group
            prev = groupEnd;
            count -= groupSize;
        }

        return dummy.next;
    }

    // Insert node at the end of the list
    public static Node insertNode(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) return newNode;
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }

    // Display the linked list
    public static void display(Node node) {
        Node curr = node;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of nodes and group size k
        int n = sc.nextInt();
        int k = sc.nextInt();
        Node head = null;

        // Input linked list elements
        for (int i = 0; i < n; i++) {
            int data = sc.nextInt();
            head = insertNode(head, data);
        }

        // Reverse in groups of k and display
        Node ans = reverseKGroup(head, k);
        display(ans);
    }
}
