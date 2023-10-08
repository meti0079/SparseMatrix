package Tonok;

import List.LinkedList;
import List.Node;

public class Shabake {

    private LinkedList people;
    private LinkedList subject;
    private Matrix realtionShip;
    private Matrix favorite;
    private   static Shabake shabake;



    private Shabake() {
        this.subject = new LinkedList();
        this.favorite=new Matrix(0,0);
        this.realtionShip= new Matrix(0,0);
        this.people=new LinkedList();
    }


    public  String findPeople(int x){
        Node node= people.getHead();
        int y=0;
        while (y != x){
            y++;
            node=node.getNext();
        }
        return  (String) node.getValue();

    }



    public  static  Shabake getInsist(){
        if (shabake==null){
            shabake=new Shabake();
        }
            return shabake;
    }

    private boolean containe(String name , LinkedList list){
        Node head=list.getHead();
        while (head!= null){
            if(((String)head.getValue()).equals(name))
                return true;
            head=head.getNext();
        }
        return  false;
    }

    public void addPeople(String name){
        if(!containe(name,people))
            people.add(name);

    }
    public void addSubject(String name){
        if(!containe(name,subject))
            subject.add(name);

    }

    public LinkedList getPeople() {
        return people;
    }

    public void setPeople(LinkedList people) {
        this.people = people;
    }

    public LinkedList getSubject() {
        return subject;
    }

    public void setSubject(LinkedList subject) {
        this.subject = subject;
    }

    public Matrix getRealtionShip() {
        return realtionShip;
    }

    public void setRealtionShip(Matrix realtionShip) {
        this.realtionShip = realtionShip;
    }

    public Matrix getFavorite() {
        return favorite;
    }

    public void setFavorite(Matrix favorite) {
        this.favorite = favorite;
    }
}
