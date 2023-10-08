package List;

import Tonok.Deraye;

public class LinkedList <T>{

     private Node head;
    private int lenght;
    private Node tail;

    public < T > LinkedList(){
        head=null;
        tail=null;
        lenght=0;
    }
    public Object add( Object  a ){
        if(head== null){
            head=new Node(a);
            tail=head;
            head.setNext(null);
        }else{
        tail.setNext(new Node(a));
        tail=tail.getNext();
        tail.setNext(null);
        }
        lenght++;
        return tail.getValue();
    }

    public void insertAfter(Node first,Node after){
        after.setNext(first.getNext());
        first.setNext(after);
        lenght++;
        if(after.getNext()==null)
            tail=after;
    }


//    public Node find(){
//        Node x=head;
//        while ( x!= null ){
//            x=x.getNext();
//        }
//        return  x;
//    }
//
    public boolean contain(String val){
        Node x=head;
        while (x!=null && x.getValue()!=val){
            x=x.getNext();
        }
        if(x!=null)
            return  true;
        else
            return false;

    }


    public  int indexOf(String val){
        Node head=this.getHead();
        int insex=0;
        while (head!=null){
            if(((String)head.getValue()).equalsIgnoreCase(val)){
                return insex;
            }
            head=head.getNext();
            insex++;
        }
        return -1;
    }


    public  Object getNodeObjext(int  a){
        Node x=this.getHead();
        int n=a;
        while (n>0) {
            x = x.getNext();
            n--;
        }
        return x.getValue();
    }


    public void addDeraye(Deraye deraye, int rc, boolean isRow){
        ///// found the curect row or col
        Node head= this.head;
        int thisSel=0;
        int nextSel=0;
            if(isRow){
                thisSel=((Deraye)head.getValue()).getCol();
            }else{
                thisSel=((Deraye)head.getValue()).getRow();
            }
            if(thisSel>rc){
                Node newHead=new Node(deraye);
                newHead.setNext(getHead());
                this.setHead(newHead);
                lenght++;
                return;
            }
            while (head.getNext()!=null){
                if(isRow){
                    nextSel=((Deraye)head.getNext().getValue()).getCol();
                }else{
                    nextSel=((Deraye)head.getNext().getValue()).getRow();
                }
                if(thisSel<rc &&nextSel>rc ){
                    insertAfter(head,new Node(deraye));
                    lenght++;
                    return;
                }
                head=head.getNext();
            }
            insertAfter(head,new Node(deraye));
            lenght++;
    }

    public boolean isEmpty(){
        return lenght==0;
    }
    public Node getTail(){
        return tail;
    }
    public Node getHead() {
        return head;
    }
    public void setHead(Node head) {
        this.head = head;
    }
    public int getLenght() {
        return lenght;
    }
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
    public void setTail(Node tail) {
        this.tail = tail;
    }

}
