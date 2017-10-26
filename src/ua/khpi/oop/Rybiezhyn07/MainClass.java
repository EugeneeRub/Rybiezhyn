package ua.khpi.oop.Rybiezhyn07;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Main class that start the work with classes
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class MainClass {

    public static void main(String[] args) {
        boolean flag = true;
        String str = System.getProperty("console.Encoding", "utf-8");
        if (str != null) {
            try {
                System.setOut(new PrintStream(System.out, true, str));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        PoliceCardBoard cardBoard = new PoliceCardBoard(1);
        Scanner in = new Scanner(System.in);
        ConsoleWork work = new ConsoleWork();

        do{
            System.out.println("Введите комманду:");
            String command = in.next();
            switch (command){
                case "add":
                    cardBoard.addPrisoner(work.addPrisonerFromConsole());
                    break;
                case "remove":
                    System.out.print("Введите номер заключенного: ");
                    cardBoard.removePrisoner(in.nextInt());
                    break;
                case "print":
                    work.printArray(cardBoard);
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command");
                    System.out.println("Try again");
                    break;
            }
        }while(flag);
    }
}