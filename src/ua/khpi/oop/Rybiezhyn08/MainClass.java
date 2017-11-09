package ua.khpi.oop.Rybiezhyn08;

import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.PoliceCardBoard;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Scanner;

/**
 * Main class that start the work with classes
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class MainClass {

    public static void main(String[] args) {
       //try {
       //    /**
       //     * Set encode that have a computer
       //     * */
       //    System.setOut(new PrintStream(System.out,true,System.getProperty("consoleEncoding")));
       //} catch (UnsupportedEncodingException e) {
       //    e.printStackTrace();
       //}
        boolean flag = true;
        PoliceCardBoard cardBoard = new PoliceCardBoard(1);
        Scanner in = new Scanner(System.in);
        ConsoleWork work = new ConsoleWork();

        do{
            System.out.println("Введите комманду:");
            String command = in.next();
            switch (command){
                case "-add":
                    cardBoard.addPrisoner(work.addPrisonerFromConsole());
                    System.out.println("Готово");
                    break;
                case "-remove":
                    System.out.print("Введите номер заключенного: ");
                    cardBoard.removePrisoner(in.nextInt());
                    System.out.println("Готово");
                    break;
                case "-print":
                    work.printArray(cardBoard);
                    break;
                case "-writetofile":
                    String way = getWay();
                    WriteToJSON write = new WriteToJSON();
                    write.writeToFile(cardBoard,way);
                    System.out.println("Готово");
                    break;
                case "-readfromfile":
                    String way2 = getWay();
                    ReadFromJSON read = new ReadFromJSON();
                    cardBoard = read.readFromFile(way2);
                    System.out.println("Готово");
                    break;
                case "-xmlto":
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Введите путь для файла: ");
                    String str1 = in1.next();
                    System.out.print("Введите индекс объекта: ");
                    int index = in1.nextInt();
                    setPrisonerToXml(cardBoard.get(index),str1);
                    System.out.println("Готово");
                    break;
                case "-xmlfrom":
                    Scanner in2 = new Scanner(System.in);
                    System.out.print("Введите путь для файла: ");
                    String str = in2.next();
                    PrisonerInfo timePrisoner = getPrisonerFromXml(str);
                    System.out.println("Готово");
                    work.printPrisoner(timePrisoner);
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command");
                    System.out.println("Try again");
                    break;
            }
        }while(true);
    }

    /**
     * Method that call method generating way from console
     *
     * @return string return way
     * */
    private static String getWay(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите тип и путь к директории");
        String command = in.next();
        try {
            return writeOrReadMethod(command).toString();
        }catch (NullPointerException e){
            System.out.println("Не введен путь к файлу");
        }
        return null;
    }

    /**
     * Method that generate way from console
     *
     * @return stringbuilder return way
     * */
    private static StringBuilder writeOrReadMethod(String s) {
        boolean flag = true;
        StringBuilder way = new StringBuilder();// new way from console
        Scanner in = new Scanner(System.in);// get way or file from console
        String arr[] = s.split("\\\\");// splited way that get from arguments
        String backdir = arr[arr.length-1];// last direction

        way.append(s);

        if (backdir.matches("(.*)\\.json")) return way;

        do {
            System.out.println(way);
            File file = new File(way.toString());
            String[] list = file.list();
            {
                if (list != null) {
                    System.out.println("Список файлов");
                    for (String s1 : list) {
                        System.out.println(s1);
                    }
                } else {
                    if (backdir.equals("")){
                        return null;
                    }
                    way.delete(way.length() - backdir.length() - 1,way.length());
                    System.out.println("Нет директории по такому пути");
                    System.out.println("Возврат к предыдущей папке");
                }
            }
            System.out.print("Папка или файл находящиеся в тек.папке:  ");
            String str = in.next();
            if (!str.equals("-back")) {
                backdir = str;
            }
            if (str.matches("(.*)\\.json")){
                System.out.println("Запись в файл");
                way.append("\\" + str);
                break;
            } else if (str.equals("..")){
                way.delete((way.length() - backdir.length()) - 1,way.length());
                String[] array = way.toString().split("\\\\");
                backdir = array[array.length - 1];
            } else if (str.matches("(.*)")){
                way.append("\\" + str);
            }
        }while (flag);
        return way;
    }

    /**
     * Method that get object from xml file
     *
     * @return prisoner object that will be taken from file
     * */
    private static PrisonerInfo getPrisonerFromXml(String str)  {
        PrisonerInfo prisoner = null;
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(str)));
            prisoner = (PrisonerInfo) decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return prisoner;
    }
    /**
     * Method that set object to xml file
     *
     * */
    private static void setPrisonerToXml(PrisonerInfo info,String way) {
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(way)));
            encoder.writeObject(info);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}