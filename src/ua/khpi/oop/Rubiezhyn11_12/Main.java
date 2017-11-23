package ua.khpi.oop.Rubiezhyn11_12;

import ua.khpi.oop.Rubiezhyn05.MyArrayList;
import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.File;
import java.util.Scanner;

/**
 * Class that start work
 * use the do-while and switch-case for command
 * also this class work with auto-mode
 *
 * @author Rubezhin Evgeniy
 * Data 23.11.2017
 * */
public class Main {
    private static MyArrayList<PrisonerInfo> cList = new MyArrayList<>();// list of prisoners
    // down line is an array of command
    private static String cArrayOfCommand[] = {"-add","-remove","-print","-readFromFile","-writeInFile","-stop"};

    /**
     * start method
     * main work is here
     * */
    public static void main(String[] args) {
        boolean mAutoFlag = false; // flag for single/auto work
        boolean mFlag = true;// flag for stop method
        Scanner mIn;// read data fom console
        String mStr;// chooser for switch-case signature
        do {
            System.out.println("Input the command");
            if (!mAutoFlag) {
                mIn = new Scanner(System.in);
                mStr = mIn.next();
            } else {
                mStr = cArrayOfCommand[(int) (0 + Math.random() * cArrayOfCommand.length)];
            }
            switch (mStr) {
                case "-finder":
                    if (cList != null || cList.size() != 0) {
                        FinderInText finder = new FinderInText();
                        ConsoleWork work = new ConsoleWork();
                        mIn = new Scanner(System.in);
                        System.out.println("Input regex for russian text");
                        String tempRus = mIn.nextLine();
                        //String tempRus = "(Тату|тату|Татуировку|татуировку|Татуировка|татуировка|татуировкой)"+
                        //" (в|с)? (виде|вида|форме) (своего|своим) (имени|именем)";
                        System.out.println("Input regex for english text");
                        String tempEng = mIn.nextLine();
                        //String tempEng = "((Tattoo|tattoo) with (his|him) name)";
                        for (PrisonerInfo prisoner : cList) {
                            if (finder.finder(prisoner,tempRus,tempEng)) {
                                work.printPrisoner(prisoner);
                            }
                        }
                    }
                    break;
                case "-add":
                    System.out.println("------Add element------");
                    if (!mAutoFlag) {
                        try {
                            cList.add(ReadFromWithRegex.readFromConsole());
                        } catch (NotMatchedDataExeption ex) {
                            ex.printError();
                        }
                    } else {
                        System.out.println("------auto work------");
                        if (cList != null || cList.size() != 0) {
                            cList.add((PrisonerInfo) cList.get((int) (0 + Math.random() * cList.size())));
                        }
                    }
                    break;
                case "-remove":
                    System.out.println("------Remove element------");
                    if (!mAutoFlag) {
                        mIn = new Scanner(System.in);
                        System.out.print("Input the index for delete the element: ");
                        cList.removeByIndex(mIn.nextInt());
                        System.out.println();
                    } else {
                        System.out.println("------auto work------");
                        if (cList != null || cList.size() != 0) {
                            int index = (int) (0 + Math.random() * cList.size());
                            System.out.println("deleting index = " + index);
                            cList.removeByIndex(index);
                        }
                    }
                    break;
                case "-print":
                    System.out.println("------Print elements------");
                    printData();
                    break;
                case "-readFromFile":
                    System.out.println("------Read from file------");
                    if (!mAutoFlag) {
                        mIn = new Scanner(System.in);
                        System.out.println("Input the way to file");
                        try {
                            cList = ReadFromWithRegex.readFromFile(new File(mIn.next()));
                        } catch (NotMatchedDataExeption notMatchedDataExeption) {
                            notMatchedDataExeption.printStackTrace();
                        }
                    } else {
                        try {
                            System.out.println("load out data from prisoners.json file");
                            cList = ReadFromWithRegex.readFromFile(new File("D:\\prisoners.json"));
                        } catch (NotMatchedDataExeption notMatchedDataExeption) {
                            notMatchedDataExeption.printStackTrace();
                        }
                    }
                     break;
                 case "-writeInFile":
                     System.out.println("------Write in file------");
                     if (!mAutoFlag) {
                         mIn = new Scanner(System.in);
                         System.out.println("Input the way to file");
                         WriteInFile.writeToFile(cList, mIn.next());
                     } else {
                         System.out.println("Load in data to autogenerate.json");
                         WriteInFile.writeToFile(cList, "D:\\autogenerate.json");
                     }
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
                case "-help":
                    System.out.println("------Help------");
                    helpInfo();
                    break;
                case "-stop":
                    System.err.println("Stop the process");
                    mFlag = false;
                    break;
                default:
                    System.out.println("Bad command");
            }
        }while (mFlag);
    }

    /**
     * Method print all about prisoner
     * by using special class that was developed myself
     * */
    private static void printData() {
        if(cList == null || cList.size() == 0) {
            System.err.println("Container is null");
            return;
        }
        ConsoleWork work = new ConsoleWork();
        for (PrisonerInfo prisoner : cList){
            work.printPrisoner(prisoner);
        }
    }

    /**
     * Help method
     * print all data in console
     * */
    private static void helpInfo() {
        System.out.println("Команда -add - для добавления нового элемента");
        System.out.println("Команда -remove - удаляет элемент по индексу");
        System.out.println("Команда -print - печатает все элементы списка");
        System.out.println("Команда -finder - находит заключенных с тату своего имени");
        System.out.println("Команда -readFromFile - чтение данных из файла");
        System.out.println("Команда -writeInFile - запись данных в файла");
        System.out.println("Команда -auto - включает авто работу");
    }
}

