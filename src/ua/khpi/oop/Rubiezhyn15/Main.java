package ua.khpi.oop.Rubiezhyn15;

import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ua.khpi.oop.Rubiezhyn10.MainClass.getPrisonerRandom;
import static ua.khpi.oop.Rubiezhyn10.MainClass.getWaySerializeble;

/**
 * Main class that start work
 *
 * @author Rubezhin Evgeniy
 * Data 07.12.2017
 * */
public class Main {
    private static ArrayList<PrisonerInfo> list;// list of prisoners
    private static String cArrayOfCommand[] = {"-addData","-removeData","-seeData","-sortData",
            "-serializeData","-deserializeData","-stop"};// arrray of command

    public static void main(String[] args) {
        ConsoleWork work = new ConsoleWork();// class console work
        boolean mAutoFlag = false;// flag for auto-single work
        boolean flag = true;// flag for main loop for getting console comand
        String mStr;// name of comand
        Scanner in;// scaner for read from console
        list = new ArrayList<>();// list of prisoners

        do {
            System.out.println("Please, input the command(input \'-help\' for help)");
            if (!mAutoFlag) {
                in = new Scanner(System.in);
                mStr = in.next();
            } else {
                mStr = cArrayOfCommand[(int) (0 + Math.random() * cArrayOfCommand.length)];
            }
            switch (mStr) {
                case "-seeData":
                    System.out.println("------See Data------");
                    printData();
                    break;
                case "-addData":
                    System.out.println("------Add Data------");
                    if (list == null)
                        break;
                    if (!mAutoFlag){
                        list.add(work.addPrisonerFromConsole());
                    } else {
                        list.add(getPrisonerRandom());
                    }
                    break;
                case "-removeData":
                    System.out.println("------Remove Data------");
                    if (list == null && list.size() < 1)
                        break;
                    if (!mAutoFlag){
                        in = new Scanner(System.in);
                        System.out.print("Input the index of element ");
                        int index = in.nextInt();
                        try{
                            list.remove(index);
                        }catch (IndexOutOfBoundsException ex){
                            System.out.println("Can`t delete this element");
                        }
                    } else {
                        try{
                            list.remove((int)(0 + Math.random()*(list.size()-1)));
                        }catch (IndexOutOfBoundsException ex){
                            System.out.println("Can`t delete this element");
                        }
                    }
                    break;
                case "-sortData":
                    System.out.println("------Sort Data------");
                    if (list != null)
                        list.sort(new myCompare());
                    break;
                case "-serializeData":
                    System.out.println("------Serialize Data------");
                    if (list != null && list.size() != 0)
                       serializeData(getWaySerializeble(mAutoFlag), list);
                    break;
                case "-deserializeData":
                    System.out.println("------Deserialize Data------");
                    list = deserializeData(getWaySerializeble(mAutoFlag));
                    break;
                case "-auto":
                    System.out.println("------Auto/Single Mode------");
                    if (mAutoFlag) {
                        System.err.println("Changed to single work");
                        mAutoFlag = false;
                    } else {
                        System.err.println("Changed to auto work");
                        mAutoFlag = true;
                    }
                    break;
                case "-stop":
                    System.out.println("------Stop Process------");
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command!!!");
                    System.out.println("Try again");
                    break;
            }
        }while (flag) ;
    }

    /**
     * Method that print data from list
     * */
    private static void printData() {
        if (list == null || list.size() == 0)
            return;
        for (PrisonerInfo p : list){
            ConsoleWork.printPrisoner(p);
        }
    }

    /**
     * Method tha load data to file
     * with serializzation
     *
     * @param list list of prisoners
     * @param way way to file
     * */
    private static void serializeData(String way, List<PrisonerInfo> list){
        if (list == null)
            return;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(way));
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method tha load data from file
     * with serializzation
     *
     * @param way way to file
     * @return ArrayList - list of prisoners
     **/
    public static ArrayList<PrisonerInfo> deserializeData(String way){
        ArrayList<PrisonerInfo> conteiner = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(way));
            conteiner = (ArrayList<PrisonerInfo>) is.readObject();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conteiner;
    }

    /**
     * Class-Comparator that used to compare objects
     * for sorting list
     *
     * @author Rubezhin Evgeniy
     * Data 07.12.2017
     * */
    public static class myCompare implements Comparator<PrisonerInfo>{
        @Override
        public int compare(PrisonerInfo o1, PrisonerInfo o2) {
            if (o1.getPerson().getMGrowth() < o2.getPerson().getMGrowth())
                return -1;
            else if (o1.getPerson().getMGrowth() > o2.getPerson().getMGrowth())
                return 1;
            return 0;
        }
    }
}
