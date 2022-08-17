package main.java.Classes;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    private static int column;
    private static String fileName;

    @Value("${airports.column}")
    public void setColumn(int value) {
        try {
            this.column = value;
        }catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("Wrong value of column");
        }
    }
    @Value("${airports.file}")
    public void setFileName(String value) {
        this.fileName = value;
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        SpringApplication.run(Main.class, args);

        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;
        System.out.println("\nВведите строку: ");
        String key = sc.nextLine();
        if(key.equals("!quit") || key.equals("!Quit")) {
            isTrue = false;
        }else {
            MyCSV rd = new MyCSV(fileName, column);
            long wastedTime = rd.outputByValue(key);
            System.out.printf("Время, затраченное на поиск и сортировку: " + (wastedTime / 1000000) + " мс");
        }
        while (isTrue) {
            System.out.println("\nВведите строку:");
            key = sc.nextLine();
            if(key.equals("!quit")) {
                isTrue = false;
            }
            if(isTrue) {
                MyCSV rd = new MyCSV(fileName, column);
                long wastedTime = rd.outputByValue(key);
                System.out.printf("Время, затраченное на поиск и сортировку: " + (wastedTime / 1000000) + " мс");
            }
        }
    }
}
