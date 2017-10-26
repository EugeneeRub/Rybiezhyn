package ua.khpi.oop.Rubiezhyn06;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

import ua.khpi.oop.Rubiezhyn05.*;

public class MainClass {

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        Scanner in = new Scanner(System.in);
        String command;
        goFromLoop:
        while (true){
            System.out.print("Введите команду: ");
            command = in.next();
            switch (command){
                case "seeData":
                    seeData(list);
                    break;
                case "serialize":
                    System.out.println("Введите путь для записи");
                    serializeData(list,in.next());
                    break;
                case "deserialize":
                    System.out.println("Введите путь для получения файла");
                    list = deserializeData(in.next());
                    break;
                case "getData":
                    System.out.println("ПРЕДУПРЕЖДЕНИЕ!");
                    System.out.println("Предыдущие данные будут утеряны");
                    System.out.println("Желаете продолжить? (Y,N)");
                    String ch = in.next();
                    if (ch.equals("Y")){
                        list = getData();
                    }
                    break;
                case "work":
                    workWithData(list);
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    break goFromLoop;
            }
        }
    }

    private static void workWithData(MyArrayList<String> list) {
        Scanner in = new Scanner(System.in);
        String command;
        goFromLoop:
        while (true){
            System.out.print("Введите команду для списка: ");
            command = in.next();
            String str;
            switch (command){
                case "addData":
                    System.out.println("Add Data");
                    in = new Scanner(System.in);
                    str = in.nextLine();
                    list.add(str);
                    break;
                case "removeData":
                    System.out.println("Remove Data");
                    in = new Scanner(System.in);
                    str = in.nextLine();
                    list.remove(str);
                    break;
                case "sortData":
                    System.out.println("Sort Data");
                    list.sort();
                    break;
                case "clearData":
                    System.out.println("Clear Data");
                    list.clear();
                    break;
                case "toString":
                    System.out.println(list.toString());
                    break;
                case "seeData":
                    System.out.println("See Data");
                    int counter = 0;
                    for (String sstring : list){
                        System.out.println(counter + "." + sstring);
                    }
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    break goFromLoop;
            }
        }
    }

    private static void help() {
        System.out.println("Команда seeData - показывает данные контейнера");
        System.out.println("Команда serialize - сериализирует данные контейнера");
        System.out.println("Команда deserialize - десириализирует данные для контейнера");
        System.out.println("Команда getData - запись данных в контейнер");
        System.out.println("Команда help - помощь с командами");
        System.out.println("Команда work - работа со списком");
        System.out.println("Команда exit - завершение работы");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Команды для работы со списком");
        System.out.println("Команда seeData - просмотр инфы в списке");
        System.out.println("Команда addData - добавление єлемента в список");
        System.out.println("Команда removeData - удаление єлемента в список");
        System.out.println("Команда clearData - очистка элементов списока");
        System.out.println("Команда toString - очистка элементов списока");
    }

    private static void serializeData(MyArrayList<String> list, String way) {
        if (list == null){
            System.out.println("Невозможна сериализация из-за пустого списка");
            return;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(way));
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            System.out.println("Запись невозможна");
        }
    }

    private static MyArrayList<String> deserializeData(String way) {
        MyArrayList<String> list = null;
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(way));
            list = (MyArrayList<String>) stream.readObject();
        } catch (IOException e) {
            System.out.println("Запись невозможна");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static MyArrayList<String> getData() {
        MyArrayList<String> list = new MyArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строки");
        while (true){
            String str = in.nextLine();
            if (str.equals("stop")){
                break;
            }
            list.add(str);
        }
        System.out.println("Запись прекращена");
        return list;
    }

    private static void seeData(MyArrayList<String> list) {
        if (list == null){
            System.out.println("Просмотр невозможен, данных нет!");
            return;
        }
        for (Iterator<String> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
    }
}


