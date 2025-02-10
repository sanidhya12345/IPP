import java.util.*;
public class InorderIterative {
    ArrayList<Integer> inorder(Node root){
        ArrayList<Integer> inorder=new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        Node currNode=root;
        while(true){

            if(currNode !=null){
                stack.push(currNode);
                currNode=currNode.left;
            }
            else {
                if(stack.isEmpty()) break;
                currNode=stack.pop();
                inorder.add(currNode.data);
                currNode=currNode.right;
            }
        }
        return inorder;
    }
}
class Node{
    int data;
    Node left;
    Node right;
    public Node(int data){
        this.data=data;
        this.left=null;
        this.right=null;
    }
}