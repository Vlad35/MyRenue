package main.java.Classes;

import java.util.Comparator;

public class MyComparator implements Comparator {

    private int column;
    public MyComparator(int column) {
        this.column = column;
    }


    @Override
    public int compare(Object o1, Object o2) {
        MyFullString r1 = (MyFullString) o1;
        MyFullString r2 = (MyFullString) o2;
        int x = r1.get()[column - 1].compareTo(r2.get()[column - 1]);
        return x != 0 ? x : (Integer.parseInt(r1.get()[0]) - Integer.parseInt(r2.get()[0]));
    }
}
