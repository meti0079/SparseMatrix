package Tonok;

public class Deraye {

    private int row;
    private int col;
    private double value;


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Deraye(int row, int col, double value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

}
