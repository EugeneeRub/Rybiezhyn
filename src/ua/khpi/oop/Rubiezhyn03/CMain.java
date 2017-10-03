package ua.khpi.oop.Rubiezhyn03;

import java.util.ArrayList;
import java.util.Scanner;

//public class CMain {
//    static private ArrayList<String> list = new ArrayList<>(); // список для временного хранения строк
//    static private Scanner in = new Scanner(System.in); // читаем из командной строки
//    static private String[][] mArrayOfStrings; // двумерный массив для работы с данными
//    static private StringBuilder string = new StringBuilder(); // хранение строк
//    static private String []mArrayOfSmallText = new String[3];// масив для хранения последнего результата
//    static private int[] counters = {0,0,0}; // счетчики элементов в двумерном массиве
//
//    public static  void main(String[] args) {
//
//        getTextFromConsole();// вызов ф-ции получения текста
//
//        raspredelenieTextData();// вызов ф-ции распределения строк
//
//        findMinTextsFromTextArray();// вызов ф-ции нахождения минимальных строк в группах
//
//        /* Вывод результа на консоль */
//        System.out.println("Самые маленькие строки");
//        for (int i = 0; i < 3; i++) {
//            if(counters[i] != 0){
//                System.out.println(mArrayOfSmallText[i] + " = " + mArrayOfSmallText[i].length() + " длинна строки");
//            }
//
//        }
//
//    }
//
//    /*
//     * Ввод из командной строки
//     * Проверка строки, если она равна _st то выход из цикла
//     * Строки записываются в ArrayList
//     * */
//    private static void getTextFromConsole() {
//        System.out.println("Пожалуйста введите строки(для завешения введите _st)");
//        while (true) {
//            string.append(in.next());
//            if ((string.toString()).equals("_st")) {
//                System.out.println("Завершение ввода");
//                break;
//            } else list.add(string.toString());
//            string.delete(0, string.length());// очистка StringBuilder перед новой записью
//        }
//
//        if (list.size() == 0) {
//            System.out.println("Данные не введены!!!");
//            return;
//        }
//    }
//
//    /*
//     *
//     * Проход по ArrayList и получение строки в StringBuilder
//     * Проверка по первому символу и запись в нужный участок
//     **/
//    private static void raspredelenieTextData() {
//        mArrayOfStrings = new String[3][list.size()];// инициализируем массив для хранения строк по категориям
//
//        for (int j = 0; j < list.size(); j++) {
//            string = new StringBuilder(list.get(j));
//            char ch = string.charAt(0);
//            /* Проверка Гласных букв латиницы */
//            if (ch == 65 || ch == 97
//                    || ch == 69 || ch == 101
//                    || ch == 73 || ch == 105
//                    || ch == 79 || ch == 111
//                    || ch == 85 || ch == 117) {
//
//                mArrayOfStrings[0][counters[0]] = string.toString();
//                counters[0]++;
//
//            }
//            /* Проверка Согласных букв латиницы */
//            else if ((ch >= 66 && ch <= 68)
//                    || (ch >= 98 && ch <= 100)
//                    || (ch >= 70 && ch <= 72)
//                    || (ch >= 102 && ch <= 104)
//                    || (ch >= 74 && ch <= 78)
//                    || (ch >= 106 && ch <= 110)
//                    || (ch >= 80 && ch <= 84)
//                    || (ch >= 112 && ch <= 116)
//                    || (ch >= 86 && ch <= 90)
//                    || (ch >= 118 && ch <= 122)) {
//                mArrayOfStrings[1][counters[1]] = string.toString(); // запись по счетчику
//                counters[1]++; // увеличение счетчика
//            }
//            /* Остальные символы забрасываются по умолчанию */
//            else {
//                mArrayOfStrings[2][counters[2]] = string.toString();
//                counters[2]++;
//            }
//        }
//    }
//
//
//    /*
//    * Проход по циклу для нахождения самой мальнекой стоки из всех в своем ряде
//    * */
//    private static void findMinTextsFromTextArray() {
//        for (int i = 0; i < 3; i++) {
//            int index = 0;
//            /* Проверяем на ноль, если истино то вычисляем, иначе оставляем пустотой */
//            if (counters[i] != 0) {
//                int min = (mArrayOfStrings[i][0]).length();
//                for (int j = 1; j < counters[i]; j++) {
//                    int next = (mArrayOfStrings[i][j]).length();
//                    if (min > next) {
//                        min = next;
//                        index = j;
//                    }
//                }
//                mArrayOfSmallText[i] = mArrayOfStrings[i][index]; // запись через индекс маленькой строки в массив маленьких строк
//            }
//        }
//    }
//}
