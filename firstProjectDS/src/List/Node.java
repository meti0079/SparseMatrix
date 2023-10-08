package List;

public class Node {

  private  Object value;
     private Node next=null;

    public Node(Object value){
        this.value=value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
