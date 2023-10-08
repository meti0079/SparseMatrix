package List;

import Tonok.Deraye;
import Tonok.Matrix;
import Tonok.Shabake;
import java.util.Scanner;

public class Main {
private static Shabake shabake=Shabake.getInsist();

    public  static void main(String [] a){
        Scanner scanner= new Scanner(System.in);
        int networks=scanner.nextInt();

        for (int i = 0; i < networks; i++) {
            int sub=scanner.nextInt();
            LinkedList thisNames=new LinkedList();
            LinkedList thisSubject=new LinkedList();
            for (int j = 0; j < sub; j++) {
                String s=scanner.next();
                thisSubject.add(s);
                shabake.addSubject(s);
            }
            int population=scanner.nextInt();
            for (int j = 0; j < population; j++) {
                String newName = scanner.next();
                thisNames.add(newName);
                shabake.addPeople(newName);
                int fri= scanner.nextInt();
                for (int k = 0; k < fri; k++) {
                    Dotaii dotaii=split(scanner.next());
                    int row =shabake.getPeople().indexOf(newName);
                    int col =shabake.getSubject().indexOf((String) thisSubject.getNodeObjext(dotaii.getInteger()));
                    double valu=dotaii.getD();
                    Deraye deraye=new Deraye(row,col,valu);
                    Node node=shabake.getFavorite().contaneDeraye(deraye);
                    if( node== null){
                        shabake.getFavorite().addToSeton(deraye);
                        shabake.getFavorite().addToSatr(deraye);
                    }else{
                        valu += ((Deraye)node.getValue()).getValue();
                        ((Deraye)node.getValue()).setValue(valu);
                    }

                }
            }
            int relation=scanner.nextInt();
            for (int j = 0; j < relation; j++) {
                int r=shabake.getPeople().indexOf((String)thisNames.getNodeObjext(scanner.nextInt()));
                int Mrel=scanner.nextInt();
                for (int k = 0; k < Mrel; k++) {
                    Dotaii dotaii=split(scanner.next());
                    int r1=shabake.getPeople().indexOf((String)thisNames.getNodeObjext(dotaii.getInteger()));
                    double va=dotaii.getD();
                    Deraye deraye=new Deraye(r,r1,va);
                    Node node=shabake.getRealtionShip().contaneDeraye(deraye);
                    if( node== null){
                        shabake.getRealtionShip().addToSeton(deraye);
                        shabake.getRealtionShip().addToSatr(deraye);
                    }else{
                        va += ((Deraye)node.getValue()).getValue();
                        ((Deraye)node.getValue()).setValue(va);
                    }
                }
            }
        }

        int query=scanner.nextInt();
        for (int i = 0; i < query; i++) {
            Matrix me=new Matrix(0,0);
            LinkedList subQue=new LinkedList();
            int Msub=scanner.nextInt();
            for (int j = 0; j < Msub; j++) {
                subQue.add(shabake.getSubject().indexOf(scanner.next()));
            }
            int H=scanner.nextInt();
            for (int j = 0; j < shabake.getPeople().getLenght(); j++) {
                int have = 0;
                double val=0;
                Node ne=subQue.getHead();
                for (int k = 0; k < subQue.getLenght(); k++) {
                    Node ma=shabake.getFavorite().contaneDeraye(new Deraye(j,(int)ne.getValue(),0));
                   if (ma==null){
                       break;
                   }else{
                       val+=((Deraye)ma.getValue()).getValue();
                       have++;
                   }
                   ne=ne.getNext();
                }
                if(have==subQue.getLenght()){
                    Deraye dd=new Deraye(j,j,val);
                    me.addToSatr(dd);
                    me.addToSeton(dd);
                }
            }

            LinkedList myList=new LinkedList();
            matrixToList(me,myList);
            LinkedList chaped=new LinkedList();
            chap(myList,0,chaped);

            for (int j = 0; j < H; j++) {
                me=Matrix.multiMatrix(shabake.getRealtionShip(),me);
                me=makeTrueShabake(me);
                LinkedList ml=new LinkedList();
                matrixToList(me,ml);
                chap(ml,j+1,chaped);
            }

        }


    }
    public  static  void matrixToList(Matrix matrix , LinkedList list){
        LinkedList l=matrix.getSatr();
        Node x=l.getHead();
        while (x !=null ){
            LinkedList y= (LinkedList) (x.getValue());
            Node xx=y.getHead();
            while (xx != null ){
              addToList(new Pair(shabake.findPeople(((Deraye)xx.getValue()).getRow()), ((Deraye)xx.getValue()).getValue()),list);
                      //((Deraye)xx.getValue()).getRow() +"   "+((Deraye)xx.getValue()).getCol()+"  "+ ((Deraye)xx.getValue()).getValue());
                xx=xx.getNext();
            }
            x=x.getNext();
        }
    }

    public  static Matrix makeTrueShabake(Matrix matrix){
        Matrix m=new Matrix(0,0);
        Node x=matrix.getSatr().getHead();
            while (x != null ){
            int have = 0;
            double val=0;
            LinkedList l=(LinkedList)x.getValue();
            Node xx=l.getHead();
            while (xx!= null){
                val+= ((Deraye)xx.getValue()).getValue();
                xx=xx.getNext();
            }
            Deraye dd=new Deraye(((Deraye)l.getHead().getValue()).getRow(),((Deraye)l.getHead().getValue()).getRow(),val);
            m.addToSeton(dd);
            m.addToSatr(dd);

            x=x.getNext();
        }
      return m;
    }

    public static void addToList(Pair pair ,LinkedList list){
       Node node=list.getHead();
        if(node==null){
            list.add(pair);
            return;
        }else{
            while (node!= null) {
                if (((Pair) node.getValue()).getValue() > pair.getValue() ) {
                    if( node.getNext() != null &&   ((Pair) node.getValue()).getValue() < pair.getValue()){
                        list.insertAfter(node, new Node(pair));
                        return;
                    }else if(node.getNext() == null){
                        list.insertAfter(node, new Node(pair));
                        return;
                    }
                } else if (((Pair) node.getValue()).getValue() == pair.getValue()) {
                    int k = 0;
                    String x1, x2;
                    x1 = ((Pair) node.getValue()).getName();
                    x2 = pair.getName();

                    while (k != Math.min(x1.length(), x2.length()) && (x1.charAt(k) == x2.charAt(k))) {
                        k++;
                    }
                    if (k == x1.length()) {
                        list.insertAfter(node, new Node(pair));
                        return;
                    } else if (k == x2.length()) {
                        Pair newN = (Pair) node.getValue();
                        node.setValue(pair);
                        list.insertAfter(node, new Node(newN));
                        return;
                    } else {
                        if (x1.charAt(k) > x2.charAt(k)) {
                            Pair newN = (Pair) node.getValue();
                            node.setValue(pair);
                            list.insertAfter(node, new Node(newN));
                            return;
                        } else {
                            list.insertAfter(node, new Node(pair));
                            return;
                        }
                    }
                } else {
                    if(  ((Pair) node.getValue()).getValue() < pair.getValue()){
                        Pair newN = (Pair) node.getValue();
                        node.setValue(pair);
                        list.insertAfter(node, new Node(newN));
                            return;
                    }
                }
                node=node.getNext();
        }
    }
}
    public static void chap(LinkedList l, int H, LinkedList chaped){
        Node x=l.getHead();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < H; i++) {
            stringBuilder.append("+");
        }
        String pl=stringBuilder.toString();
            while (x != null ){
                if(!chaped.contain(((Pair)x.getValue()).getName())){
                    System.out.println( ((Pair)x.getValue()).getName()+" "+  String.format("%.6f", ((Pair)x.getValue()).getValue())+" "+pl);
                    chaped.add(((Pair)x.getValue()).getName());
                }
                x=x.getNext();
            }

    }
    public  static  Dotaii split(String s){
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i)==':'){
                 return  new Dotaii(Integer.parseInt(s.substring(0,i)),Double.parseDouble(s.substring(i+1)));
                }
            }
            return  null;
        }

}
//    LinkedList l= new LinkedList();
//        for (int i = 0; i < 10; i++) {
//            l.add(i,i);
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(l.getNode(i).getValue());
//        }

//    Node x=subject.getHead();
//           while (x!=null){
//        System.out.print(x.getValue());
//        x=x.getNext();
//    }

/////    check sort
//    LinkedList list=new LinkedList();
//    Pair aa=new Pair("A",1.0);
//    Pair b=new Pair("B",1.0);
//    Pair c=new Pair("A",2.0);
//    Pair d=new Pair("AB",2.0);
//    addToList(aa,list);
//    addToList(b,list);
//    addToList(c,list);
//    addToList(d,list);
//    Node x=list.getHead();
//        for (int i = 0; i < 4; i++) {
//        System.out.println(((Pair)x.getValue()).getValue() +"  "+((Pair)x.getValue()).getName());
//        x=x.getNext();
//        }
