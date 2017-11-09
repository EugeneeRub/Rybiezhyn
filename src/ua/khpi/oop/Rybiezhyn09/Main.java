package ua.khpi.oop.Rybiezhyn09;

import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ConsoleWork work = new ConsoleWork();
        LinkConteiner<PrisonerInfo> conteiner = null;
        PrisonerInfo[] array = null;
        Scanner in = null;
        boolean flag = true;

        do {
            System.out.println("Please, input the command(input \'-help\' for help)");
            in = new Scanner(System.in);
            String n = in.next();
            switch (n){
                case "-seeData":
                    printData(conteiner);
                    break;
                case "-addData":
                    conteiner = addData(conteiner);
                    break;
                case "-removeData":
                    conteiner = removeData(conteiner);
                    break;
                case "-serializeData":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file");
                    serializeData(in.next(),conteiner);
                    break;
                case "-deserializeData":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file");
                    conteiner = deserializeData(in.next());
                    break;
                case "-xmlEncode":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file(for \'.xml\' file)");
                    encodeData(in.next(),conteiner);
                    break;
                case "-xmlDecode":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file(for \'.xml\' file)");
                    conteiner = decodeData(in.next());
                    break;
                case "-createContein":
                    conteiner = createContein(conteiner);
                    break;
                case "-toString":
                    if (conteiner != null || conteiner.size() == 0)
                        System.out.println(conteiner.toString());
                    else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-toArray":
                    array = (PrisonerInfo[]) conteiner.toArray();
                    break;
                case "-contain":
                    if (conteiner != null){
                        if (conteiner.contains(work.addPrisonerFromConsole())) {
                            System.out.println("Have same objects");
                        }else
                            System.out.println("Don`t have same objects");
                    } else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-containAll":
                    if (conteiner != null)
                        conteiner.containsAll(createListOfNewElements());
                    else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-help":
                    help();
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command!!!");
                    System.out.println("Try again");
                    break;
            }
        }while(flag);
    }

    public static LinkConteiner<PrisonerInfo> createContein(LinkConteiner<PrisonerInfo> conteiner) {
        System.out.println("Warning!!!");
        System.out.println("If you have elements in container, it will be removed");
        System.out.println("So we saved your data, on \'D:\\saved.dat\' way( or not :) )");
        if (conteiner != null)
            serializeData("D:\\saved.dat",conteiner);
        conteiner = new LinkConteiner<>();
        return conteiner;
    }

    public static LinkConteiner<PrisonerInfo> removeData(LinkConteiner<PrisonerInfo> conteiner) {
        Scanner in;
        if (conteiner != null) {
            if (!(conteiner.size() == 0)){
                in = new Scanner(System.in);
                conteiner.remove(in.nextInt());
            }else
                System.out.println("Container is null or has 0 elements");
        }else
            System.out.println("Container is null");
        return conteiner;
    }

    public static LinkConteiner<PrisonerInfo> addData(LinkConteiner<PrisonerInfo> conteiner) {
        ConsoleWork work = new ConsoleWork();
        if (conteiner == null)
            conteiner = new LinkConteiner<>();
        conteiner.add(work.addPrisonerFromConsole());
        return conteiner;
    }

    public static Collection<PrisonerInfo> createListOfNewElements() {
        LinkedList<PrisonerInfo> list = new LinkedList<>();
        ConsoleWork work = new ConsoleWork();
        Scanner in = new Scanner(System.in);
        boolean flag = true;

        do {
            switch (in.next()){
                case "-add":
                    list.add(work.addPrisonerFromConsole());
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
        }while (flag);
        return list;
    }

    public static void printData(LinkConteiner<PrisonerInfo> conteiner) {
        ConsoleWork work = new ConsoleWork();
        if (conteiner == null || conteiner.size() == 0)
            return;
        for (PrisonerInfo p : conteiner){
            work.printPrisoner(p);
        }
    }

    public static void serializeData(String way, LinkConteiner<PrisonerInfo> conteiner) {
        if (conteiner == null)
            return;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(way));
            oos.writeObject(conteiner);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void encodeData(String way, LinkConteiner<PrisonerInfo> conteiner){
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(way));
            for (PrisonerInfo info : conteiner)
                encoder.writeObject(info);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static LinkConteiner<PrisonerInfo> deserializeData(String way) {
        LinkConteiner<PrisonerInfo> conteiner = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(way));
            conteiner = (LinkConteiner<PrisonerInfo>) is.readObject();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conteiner;
    }

    public static LinkConteiner<PrisonerInfo> decodeData(String way) {
        LinkConteiner<PrisonerInfo> conteiner = new LinkConteiner<>();
        try(XMLDecoder decoder = new XMLDecoder(new FileInputStream(way))) {
            while (true){
                PrisonerInfo info = (PrisonerInfo) decoder.readObject();
                conteiner.add(info);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex){

        }
        return conteiner;

    }

    public static void help() {
        System.out.println("Команда -seeData - показывает данные контейнера");
        System.out.println("Команда -addData - добавление элемента");
        System.out.println("Команда -removeData - удаление элемента");
        System.out.println("Команда -serializeData - запись в файл(Сериализация)");
        System.out.println("Команда -deserializeData - чтение в файл(Десериализация)");
        System.out.println("Команда -xmlEncode - запись в xml файл");
        System.out.println("Команда -xmlDecode - чтение из xml файла");
        System.out.println("Команда -createContein - создание контейнера");
        System.out.println("Команда -toString - перевод в стринг строку");
        System.out.println("Команда -toArray - перевод в массив");
        System.out.println("Команда -contain - проверка элемента на присутствие в массиве");
        System.out.println("Команда -containAll - проверка на єлементы");
    }
}
