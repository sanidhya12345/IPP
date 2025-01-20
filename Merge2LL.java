import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Merge2LL {

    public static ArrayList<Integer> list=new ArrayList<>();
    public static Node insertNode(Node head,int data){
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
        }
        Node curr=head;
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=newNode;
        newNode.next=null;
        return head;
    }
    public static void displayLL(Node node){
        Node curr=node;
        while(curr!=null){
            System.out.print(curr.data+" ");
            curr=curr.next;
        }
        System.out.println();
    }
    public static Node sortedMerge(Node head1, Node head2) {
        // code here
        Node curr=head1;
        while (curr!=null){
            list.add(curr.data);
            curr=curr.next;
        }
        curr=head2;
        while(curr!=null){
            list.add(curr.data);
            curr=curr.next;
        }
        Collections.sort(list);
        Node ans=null;
        for(int i:list){
            ans=insertNode(ans,i);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Node head1 = null;
        Node head2=null;
        int m=sc.nextInt();
        int n=sc.nextInt();
        for(int i=0;i<m;i++){
            int data=sc.nextInt();
            head1=insertNode(head1,data);
        }
        for(int i=0;i<n;i++){
            int data=sc.nextInt();
            head2=insertNode(head2,data);
        }
        displayLL(sortedMerge(head1,head2));
    }
}
class Node{
    int data;
    Node next;
    public Node(int data){
        this.data=data;
        this.next=null;
    }
}
