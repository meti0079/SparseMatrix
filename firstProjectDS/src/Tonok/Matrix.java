package Tonok;

import List.LinkedList;
import List.Node;


public class Matrix {
    private LinkedList<LinkedList<Deraye>> satr;
    private LinkedList <LinkedList<Deraye>> seton;
    private  int m;
    private int n;

    public Matrix(int n, int m){
        this.m=m;
        this.n=n;
        satr=new LinkedList();
        seton= new LinkedList();
    }

    public void addToSatr(Deraye newDeraye){
    Node node=satr.getHead();
        if(node==null){
            satr.add(makeNewRowCol(newDeraye));
            return;
        }else{
            while (node!= null){
            LinkedList list=(LinkedList) node.getValue();
                if(((Deraye)list.getHead().getValue()).getRow()==newDeraye.getRow()){
                        list.addDeraye(newDeraye,newDeraye.getCol(),true);
                        return;
                }else if(((Deraye)list.getHead().getValue()).getRow()>newDeraye.getRow()){
                 Node nn=new Node(makeNewRowCol(newDeraye));
                    nn.setNext(node);
                    satr.setHead(nn);
                    return;
                }else{
                    if(node.getNext()!= null &&((Deraye)((LinkedList)node.getNext().getValue()).getHead().getValue()).getRow()>newDeraye.getRow() ){
                        satr.insertAfter(node, new Node(makeNewRowCol(newDeraye)));
                        return;
                    }
                    if(node.getNext()==null){
                        satr.insertAfter(node, new Node(makeNewRowCol(newDeraye)));
                        return;
                    }
                }
                node=node.getNext();
            }
        }
    }
    private LinkedList makeNewRowCol(Deraye deraye){
        LinkedList l=new LinkedList();
        l.add(deraye);
        return  l;
    }
    public void addToSeton(Deraye newDeraye){
        Node node=seton.getHead();
        if(node==null){
            seton.add(makeNewRowCol(newDeraye));
            return;
        }else{
            while (node!= null){
                LinkedList list=(LinkedList) node.getValue();
                if(((Deraye)list.getHead().getValue()).getCol()==newDeraye.getCol()){
                    list.addDeraye(newDeraye,newDeraye.getRow(),false);
                    return;
                }else if(((Deraye)list.getHead().getValue()).getCol()>newDeraye.getCol()){
                    Node nn=new Node(makeNewRowCol(newDeraye));
                    nn.setNext(node);
                    seton.setHead(nn);
                    return;
                }else{
                    if(node.getNext()!= null &&((Deraye)((LinkedList)node.getNext().getValue()).getHead().getValue()).getCol()>newDeraye.getCol() ){
                        seton.insertAfter(node, new Node(makeNewRowCol(newDeraye)));
                        return;
                    }
                    if(node.getNext()==null){
                        seton.insertAfter(node, new Node(makeNewRowCol(newDeraye)));
                        return;
                    }
                }
                node=node.getNext();
            }
        }
    }




    public  static  Matrix multiMatrix(Matrix A,Matrix B){
        Matrix C=new Matrix(A.getN(),B.getM());
        LinkedList Asatrs=A.getSatr();
        LinkedList Bsetons=B.getSeton();

        Node x=Asatrs.getHead();
        Node y =Bsetons.getHead();
        while (x  != null ){
            while (y != null){
              Deraye ans=  multiList((LinkedList) x.getValue(), (LinkedList) y.getValue());
              if(ans.getValue()!=0){
                  C.addToSatr(ans);
                  C.addToSeton(ans);
              }
                y=y.getNext();
            }
             y=Bsetons.getHead();
             x=x.getNext();
        }
        return  C;
    }

    public  static  Deraye multiList(LinkedList satr,LinkedList seton){
            double sum=0;
            Node x=satr.getHead();
            Node y=seton.getHead();

            while (x != null && y != null){
                    if(((Deraye)x.getValue()).getCol()==((Deraye)y.getValue()).getRow()){
                        sum +=((Deraye)x.getValue()).getValue()*((Deraye)y.getValue()).getValue();
                        x=x.getNext();
                        y=y.getNext();
                    }else if (((Deraye)x.getValue()).getCol() >((Deraye)y.getValue()).getRow()){
                        y=y.getNext();
                    }else{
                        x=x.getNext();
                    }
            }
        return new Deraye(((Deraye)satr.getHead().getValue()).getRow(),((Deraye)seton.getHead().getValue()).getCol(),sum);
    }


    public Node contaneDeraye(Deraye deraye){
        Node a= satr.getHead();
        while (a != null){
//            LinkedList list=(LinkedList)a.getValue();
            Node aa=((LinkedList)a.getValue()).getHead();
            if(((Deraye)aa.getValue()).getRow()>deraye.getRow())
                return null;
            if(((Deraye)aa.getValue()).getRow()==deraye.getRow()){
                while (aa != null){
                    if(((Deraye) aa.getValue()).getCol()  >  deraye.getCol())
                        return null;
                    if(((Deraye) aa.getValue()).getCol()==deraye.getCol())
                        return aa;
                    aa=aa.getNext();
                }
            }
            a=a.getNext();
        }
        return  null;
    }

    public LinkedList<LinkedList<Deraye>> getSatr() {
        return satr;
    }

    public void setSatr(LinkedList<LinkedList<Deraye>> satr) {
        this.satr = satr;
    }

    public LinkedList<LinkedList<Deraye>> getSeton() {
        return seton;
    }

    public void setSeton(LinkedList<LinkedList<Deraye>> seton) {
        this.seton = seton;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

//    public void addSatr(int r,int c, Double val){
//        Node x=satr.find(r);
//        if(x== null){
//            LinkedList<Double> A= new LinkedList<>();
//            A.add(val,c);
//            satr.add(A,c);
//        }else{
//            ((LinkedList)x.getValue()).add(val,c);
//        }
//    }
//    public void addSeton(int r,int c, Double val) {
//        Node x = seton.find(c);
//        if (x == null) {
//            LinkedList<Double> A = new LinkedList<>();
//            A.add(val, r);
//            seton.add(A, r);
//        } else {
//            ((LinkedList) x.getValue()).add(val, r);
//        }
//    }

}