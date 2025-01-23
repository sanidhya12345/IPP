public class Stack {
    public int MAXSIZE=5;
    public int [] stack;
    public int top;
    public Stack(){
        stack=new int[MAXSIZE];
        top=-1;
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public boolean isFull(){
        return top==MAXSIZE-1;
    }
    public void push(int data){
        if(isFull()){
            System.out.println("Stack is full not able to insert");
            return;
        }
        top=top+1;
        stack[top]=data;
    }
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is empty insert some element to delete");
            return -1;
        }
        int ele=stack[top];
        top=top-1;
        return ele;
    }
    public int peek(){
        return stack[top];
    }
    public static void main(String[] args) {
        Stack st=new Stack();
        st.push(23);
        st.push(34);
        st.push(56);
        st.push(12);
        st.push(80);
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        st.pop();
    }
}
