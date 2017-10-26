package ua.khpi.oop.Rubiezhyn04;

import java.util.ArrayList;
import java.util.Scanner;

/**
* Разработчик: Евгений Рубежин
* Группа: КИТ-16а
* Индивидуальное задание:
* Ввести несколько строк. Разбить строки на три группы: начинается с гласной; начинается с согласной; начинается не с буквы.
* Найти кратчайший строку в каждой группе. Вывести эту строку и его длины.
*
* Требования:
*
* Используя программу решения задачи лабораторной работы №3,
* в соответствии с прикладной задачи обеспечить обработку команд пользователя в виде текстового меню:
* ввода данных;
* просмотр данных;
* выполнения вычислений;
* отображение результата;
* завершение программы и т.д.
*
* Обеспечить обработку параметров командной строки для определения режима работы программы:
* параметр "-h" или "-help": отображается информация об авторе программы, назначения (индивидуальное задание),
* детальное описание режимов работы (пунктов меню и параметров командной строки)
* параметр "-d" или "-debug": в процессе работы программы отображаются дополнительные данные,
* облегчающие отладку и проверку работоспособности программы: диагностические сообщения, промежуточные значения переменных,
* значение временных переменных и др.
*
*/
public class CMainEntry {
    static private boolean flag = true; // флаг для работы цикла
    static private boolean flagForMethod = false; // флаг для запуска дебага
    static private ArrayList<String> list = new ArrayList<>(); // список для временного хранения строк
    static private Scanner in = new Scanner(System.in); // читаем из командной строки
    static private String[][] mArrayOfStrings; // двумерный массив для работы с данными
    static private StringBuilder text = new StringBuilder(); // хранение строк
    static private String[] mArrayOfSmallText = new String[3];// масив для хранения последнего результата
    static private int[] counters = {0,0,0}; // счетчики элементов в двумерном массиве

    public static void main(String[] args) {
        do {
            System.out.println("Напишите действие программы(-h или -help для помощи):");
            String name = in.next();
            switch (name) {
                case "input":
                    System.out.println("Запись данных");
                    getTextFromConsole(flagForMethod);// вызов ф-ции получения текста
                    break;
                case "seeData":
                    System.out.println("Чтение данных");
                    seeDataMethod(flagForMethod);
                    break;
                case "work":
                    callMethod(flagForMethod);
                    break;
                case "seeResult":
                    System.out.println("Показ результата");
                    seeResultFromReadyArray(flagForMethod);
                    break;
                case "stop":
                    System.out.println("Прекращение програмы");
                    flag = false;
                    break;
                case "-d":
                    changeFlag();
                    break;
                case "-debug":
                    changeFlag();
                    break;
                case "-h":
                    printInfo();
                    break;
                case "-help":
                    printInfo();
                    break;
                default:
                    System.out.println("Некоректно введены данные!");
                    System.out.println("Повторите попытку");
                    break;
            }
        }while (flag);
    }
    /**
     * Метод смены флагов для работы debug mode
     **/
    private static void changeFlag(){
        if (flagForMethod){
            System.out.println("Отключение режима debug");
            System.out.println("Вызовите метод еще раз чтобы вернуть режим debug");
            flagForMethod = false;
        } else {
            System.out.println("Переключение в режим debug");
            System.out.println("Вызовите метод еще раз чтобы отключить");
            System.out.println("Следующие вызовы методов будут показывать данные debug");
            flagForMethod = true;
        }
    }

    /**
     * Метод вывода инормации по вводу -h или -help
     **/
    private static void printInfo(){
        System.out.println("Автор программы: Рубежин Евгений");
        System.out.println("Группа: КИТ-16а");
        System.out.print("Индивидуальное задание:\nВвести несколько строк. ");
        System.out.print("Разбить столбцы на три группы: начинается с гласной, ");
        System.out.print("начинается с согласной, начинается не с буквы. ");
        System.out.print("Найти самую меньшую строку в каждой группе. ");
        System.out.print("Ввести эту строку и ее длину\n\n");
        System.out.println("Точное описание программ:");
        System.out.print("\"input\" - ввод данных в массив\n");
        System.out.print("\"seeData\" - просмотр строк перед вычислением\n");
        System.out.print("\"work\" - запуск метода обработки строк\n");
        System.out.print("\"seeResult\" - просмотр результата после обработки строк\n");
        System.out.print("\"stop\" - остановка работы программы\n");
        System.out.print("\"-d\" или \"-debug\" - включение показа отладки после вызова методов\n");
        System.out.print("\"-h\" или \"-help\" - показ информации\n");
    }

    /**
     * Метод просмотра данных после обработки
     **/
    private static void seeResultFromReadyArray(boolean flag) {
        if (!flag) {
            if (mArrayOfSmallText[0] == null && mArrayOfSmallText[1] == null && mArrayOfSmallText[2] == null) {
                System.out.println("Данные не обработаны");
                return;
            }
        /* Вывод результа на консоль */
            System.out.println("Самые маленькие строки");
            for (int i = 0; i < 3; i++) {
                if (counters[i] != 0) {
                    System.out.println(mArrayOfSmallText[i] + "; " + "длинна строки = " + mArrayOfSmallText[i].length());
                }
            }
        } else {
            System.out.println("------------------------- debug start -------------------------------");
            System.out.println("--debug--: проверка условия (mArrayOfSmallText[0] == null && mArrayOfSmallText[1] == null && mArrayOfSmallText[2] == null)");// for debug point
            System.out.println("--debug--: значения mArrayOfSmallText = 1) " + mArrayOfSmallText[0] + "; 2) " + mArrayOfSmallText[1] + "; 3) " + mArrayOfSmallText[2]);// for debug point
            if (mArrayOfSmallText[0] == null && mArrayOfSmallText[1] == null && mArrayOfSmallText[2] == null) {
                System.out.println("-------------------------------------");
                System.out.println("Данные не обработаны");
                System.out.println("-------------------------------------");
                return;
            }
            /* Вывод результа на консоль */

            System.out.println("-------------------------------------");
            System.out.println("Самые маленькие строки");
            System.out.println("-------------------------------------");
            System.out.println("--debug--: запуск цикла");
            for (int i = 0; i < 3; i++) {
                System.out.println("--debug--: counters[" + i + "] = " + counters[i]);
                if (counters[i] != 0) {
                    System.out.println(mArrayOfSmallText[i] + "; " + "длинна строки = " + mArrayOfSmallText[i].length());
                }
            }
            System.out.println("------------------------- debug end -------------------------------");
        }
    }

    /**
     * Метод просмотра данных перед обработкой
     **/
    private static void seeDataMethod(boolean flag) {
        if (!flag) {
            if (list.size() == 0) {
                System.out.println("Данные пустые, строк нет!");
                return;
            }
            System.out.println("Просмотр данных:");
            for (String str : list) {
                System.out.print(str + " ");// вывод результата
            }
            System.out.println("");// сделан для дополнительного добавления enter

        } else {
            System.out.println("------------------------- debug start -------------------------------");
            System.out.println("--debug--: list(ArrayList) = " + list.size());// for debug point
            if (list.size() == 0) {
                System.out.println("--debug--: вход в условие list.size() == 0");// for debug point
                System.out.println("-------------------------------------");
                System.out.println("Данные пустые, строк нет!");
                System.out.println("-------------------------------------");
                return;
            }

            System.out.println("--debug--: работа цикла выдачи данных");// for debug point
            System.out.println("-------------------------------------");
            System.out.println("Просмотр данных:");
            System.out.println("-------------------------------------");
            for (String str : list) {

                System.out.println("--debug--: str(String) = " + str);// for debug point
                System.out.println("-------------------------------------");
                System.out.println(str + " ");// вывод результата
                System.out.println("-------------------------------------");
            }
            System.out.println("");// сделан для дополнительного добавления enter
            System.out.println("------------------------- debug end -------------------------------");
        }
    }

    /**
     * Ввод из командной строки
     * Проверка строки, если она равна _st то выход из цикла
     * Строки записываются в ArrayList
     * */
    private static void getTextFromConsole(boolean flag) {

        text.delete(0,text.length()); // очищаем текст для будущей работы обработки
        mArrayOfStrings = null; // обнулем массив текстов
        list.clear(); // очищаем список для заполнения из консоли

        if(!flag){
            System.out.println("Пожалуйста введите строки(для завешения введите _st)");
            while (true) {
                text.append(in.next());
                if ((text.toString()).equals("_st")) {
                    System.out.println("Завершение ввода");
                    break;
                } else list.add(text.toString());
                text.delete(0, text.length());// очистка StringBuilder перед новой записью
            }

            if (list.size() == 0) {
                System.out.println("Данные не введены!!!");
                return;
            }
        }else {
            System.out.println("------------------------- debug start -------------------------------");
            System.out.println("-------------------------------------");
            System.out.println("Пожалуйста введите строки(для завешения введите _st)");
            System.out.println("-------------------------------------");
            while (true) {
                text.append(in.next());
                System.out.println("--debug--: text = " + text.toString());// for debug point
                if ((text.toString()).equals("_st")) {
                    System.out.println("--debug--: Прекращение ввода");// for debug point
                    System.out.println("-------------------------------------");
                    System.out.println("Завершение ввода");
                    System.out.println("-------------------------------------");
                    break;
                } else {
                    System.out.println("--debug--: list = str(" + text.toString() + ")");// for debug point
                    list.add(text.toString());
                }
                System.out.println("--debug--: очитска text перед записью");// for debug point
                text.delete(0, text.length());// очистка StringBuilder перед новой записью
            }

            if (list.size() == 0) {
                System.out.println("-------------------------------------");
                System.out.println("Данные не введены!!!");
                System.out.println("-------------------------------------");
                return;
            }
            System.out.println("------------------------- debug end -------------------------------");
        }
    }

    /**
     * Проход по ArrayList и получение строки в StringBuilder
     * Проверка по первому символу и запись в нужный участок
     **/
    private static void raspredelenieTextData(boolean flag) throws CMyExeption {

        for (int i = 0; i < 3; i++) {
            counters[i] = 0; // обнуляем счетчик
        }

        if (!flag) {

            if (list.size() == 0) throw new CMyExeption();
            mArrayOfStrings = new String[3][list.size()];// инициализируем массив для хранения строк по категориям

            for (String str : list) {
                text = new StringBuilder(str);
                char ch = text.charAt(0);
            /* Проверка Гласных букв латиницы */
                if (ch == 65 || ch == 97
                        || ch == 69 || ch == 101
                        || ch == 73 || ch == 105
                        || ch == 79 || ch == 111
                        || ch == 85 || ch == 117) {

                    mArrayOfStrings[0][counters[0]] = text.toString();
                    counters[0]++;
                }
            /* Проверка Согласных букв латиницы */
                else if ((ch >= 66 && ch <= 68)
                        || (ch >= 98 && ch <= 100)
                        || (ch >= 70 && ch <= 72)
                        || (ch >= 102 && ch <= 104)
                        || (ch >= 74 && ch <= 78)
                        || (ch >= 106 && ch <= 110)
                        || (ch >= 80 && ch <= 84)
                        || (ch >= 112 && ch <= 116)
                        || (ch >= 86 && ch <= 90)
                        || (ch >= 118 && ch <= 122)) {
                    mArrayOfStrings[1][counters[1]] = text.toString(); // запись по счетчику
                    counters[1]++; // увеличение счетчика
                }
            /* Остальные символы забрасываются по умолчанию */
                else {
                    mArrayOfStrings[2][counters[2]] = text.toString();
                    counters[2]++;
                }
            }
        } else {
            System.out.println("------------------------- debug start -------------------------------");
            System.out.println("-------------------------------------");
            System.out.println("--debug--: list.size() = " + list.size());// for debug point
            if (list.size() == 0) throw new CMyExeption();
            System.out.println("--debug--: инициализация mArrayOfStrings");// for debug point
            mArrayOfStrings = new String[3][list.size()];// инициализируем массив для хранения строк по категориям

            for (String str : list) {
                text = new StringBuilder(str);
                System.out.println("--debug--: text = " + text.toString());// for debug point
                char ch = text.charAt(0);
                System.out.println("--debug--: ch = " + ch);// for debug point
            /* Проверка Гласных букв латиницы */
                if (ch == 65 || ch == 97
                        || ch == 69 || ch == 101
                        || ch == 73 || ch == 105
                        || ch == 79 || ch == 111
                        || ch == 85 || ch == 117) {
                    int c = ch;
                    System.out.println("--debug--: символ ch = " + c + " в цифровом виде");// for debug point
                    mArrayOfStrings[0][counters[0]] = text.toString();
                    System.out.println("--debug--: увеличиваем счетчик " + "counters[" + 0 + "]++");// for debug point
                    counters[0]++;
                }
            /* Проверка Согласных букв латиницы */
                else if ((ch >= 66 && ch <= 68)
                        || (ch >= 98 && ch <= 100)
                        || (ch >= 70 && ch <= 72)
                        || (ch >= 102 && ch <= 104)
                        || (ch >= 74 && ch <= 78)
                        || (ch >= 106 && ch <= 110)
                        || (ch >= 80 && ch <= 84)
                        || (ch >= 112 && ch <= 116)
                        || (ch >= 86 && ch <= 90)
                        || (ch >= 118 && ch <= 122)) {
                    int c = ch;
                    System.out.println("--debug--: символ ch = " + c + " в цифровом виде");// for debug point
                    mArrayOfStrings[1][counters[1]] = text.toString(); // запись по счетчику
                    System.out.println("--debug--: увеличиваем счетчик " + "counters[" + 1 + "]++");// for debug point
                    counters[1]++; // увеличение счетчика
                }
            /* Остальные символы забрасываются по умолчанию */
                else {
                    int c = ch;
                    System.out.println("--debug--: символ ch = " + c + " в цифровом виде");// for debug point
                    mArrayOfStrings[2][counters[2]] = text.toString();
                    System.out.println("--debug--: увеличиваем счетчик " + "counters[" + 2 + "]++");// for debug point
                    counters[2]++;
                }
            }
            System.out.println("------------------------- debug end -------------------------------");
        }

    }

    /**
    * Проход по циклу для нахождения самой мальнекой стоки из всех в своем ряде
    * */
    private static void findMinTextsFromTextArray(boolean flag) throws CMyExeption {
        if (!flag) {
            for (int i = 0; i < 3; i++) {
                int index = 0;
            /* Проверяем на ноль, если истино то вычисляем, иначе оставляем пустотой */
                if (counters[i] != 0) {
                    int min = (mArrayOfStrings[i][0]).length();
                    for (int j = 1; j < counters[i]; j++) {
                        int next = (mArrayOfStrings[i][j]).length();
                        if (min > next) {
                            min = next;
                            index = j;
                        }
                    }
                    mArrayOfSmallText[i] = mArrayOfStrings[i][index]; // запись через индекс маленькой строки в массив маленьких строк
                }
            }
        } else {
            System.out.println("------------------------- debug start -------------------------------");
            for (int i = 0; i < 3; i++) {
                int index = 0;
                System.out.println("--debug--: текущий index = " + index);// for debug point
            /* Проверяем на ноль, если истино то вычисляем, иначе оставляем пустотой */
                System.out.println("--debug--: текущий counters[i] = " + counters[i]);// for debug point
                if (counters[i] != 0) {
                    int min = (mArrayOfStrings[i][0]).length();
                    System.out.println("--debug--: текущий min = " + min);// for debug point
                    for (int j = 1; j < counters[i]; j++) {
                        int next = (mArrayOfStrings[i][j]).length();
                        System.out.println("--debug--: текущий next = " + next);// for debug point
                        if (min > next) {
                            min = next;
                            System.out.println("--debug--: текущий next = " + min);// for debug point
                            index = j;
                            System.out.println("--debug--: текущий index = " + index);// for debug point
                        }
                    }
                    mArrayOfSmallText[i] = mArrayOfStrings[i][index]; // запись через индекс маленькой строки в массив маленьких строк
                    System.out.println("--debug--: текущий mArrayOfSmallText[" + i + "] = " + mArrayOfSmallText[i]);// for debug point
                }
            }
            System.out.println("------------------------- debug end -------------------------------");
        }
    }

    /**
     * Метод, для вызова методов обработки строк
     * Испульзуются Exception для поимки ошибок если списко строк пуст
     */
    private static void callMethod(boolean flag) {
        try {
            raspredelenieTextData(flagForMethod);// вызов ф-ции распределения строк
            findMinTextsFromTextArray(flagForMethod);// вызов ф-ции нахождения минимальных строк в группах
        } catch (CMyExeption ex) {
            ex.printError();
        }

    }

    /**
     * Класс наследник Exception для вывода ошибок
     */
    private static class CMyExeption extends Exception {
        private void printError() {
            System.out.println("Невозможно форматировать!!!");
        }
    }
}



