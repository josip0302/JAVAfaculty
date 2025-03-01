package hr.fer.zemris.java.gui.layouts;

import java.util.Objects;

public class RCPosition {
    private int row,column;

    public RCPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public static RCPosition parse(String text){

        String[] a=text.strip().split(",");
        if(a.length!=2 ){
            throw new IllegalArgumentException();
        }
        try {

            RCPosition rez = new RCPosition(Integer.valueOf(a[0]), Integer.valueOf(a[1]));
            return rez;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RCPosition position = (RCPosition) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
