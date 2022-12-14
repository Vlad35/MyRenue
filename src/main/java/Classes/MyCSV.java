package main.java.Classes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MyCSV {
    private int column;
    private String fileName;

    public MyCSV(String fileName, int column) {
        this.column = column;
        this.fileName = fileName;
    }

    private boolean begin(String beg, String str) {
        if(beg.length() <= str.length()) {
            boolean t = true;
            for(int i = 0; i < beg.length(); i++) {
                boolean ifIs = beg.charAt(i) == str.charAt(i);
                if(beg.charAt(i) >= 97) {
                    t = t && (ifIs || ((beg.charAt(i) - 32) == str.charAt(i)));
                }else {
                    t = t && (ifIs);
                }
            }
            return t;
        } else {return false;}
    }

    public long outputByValue(String key) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new FileReader(fileName, StandardCharsets.UTF_8));
        String[] nextLine;
        Comparator comparator = new MyComparator(column);
        Set<MyFullString> set = new TreeSet<MyFullString>(comparator);
        long startTime = System.nanoTime();
        while ((nextLine = csvReader.readNext()) != null) {
            if(nextLine != null) {
                if(begin(key,nextLine[column - 1])) {
                    MyFullString row = new MyFullString(nextLine);
                    set.add(row);
                }
            }
        }
        long endTime = System.nanoTime();

        Iterator<MyFullString> iterator = set.iterator();
        while (iterator.hasNext()) {
            String[] row = iterator.next().get();
            System.out.println();
            System.out.print("<" + row[column - 1] + ">[<");
            for(int i = 1; i < row.length - 1; i++) {
                if(i != column - 1) {
                    System.out.print(row[i] + ", ");
                }
            }
            System.out.print(row[row.length - 1] + ">]\n");
        }
        System.out.println("???????????????????? ??????????: " + set.size() + ".");
        long resultTime = endTime - startTime;
        return resultTime;
    }
}
