package ua.khpi.oop.Rubiezhyn05;
import java.util.Scanner;

/**
 * @author  Евгений Рубежин
 * Группа: КИТ-16а
 * Вимоги
 *
 * 1) Реалізувати і продемонструвати тривале зберігання/відновлення раніше
 * розробленого контейнера за допомогою серіалізації/десеріалізації.
 * 2) Обмінятися відкомпільованим (без початкового коду) службовим класом (Utility Class)
 * рішення задачі л.р. №3 з іншим студентом (визначає викладач).
 * 3) Продемонструвати послідовну та вибіркову обробку елементів розробленого
 * контейнера за допомогою власного і отриманого за обміном службового класу.
 * 4) Реалізувати та продемонструвати порівняння, сортування та пошук елементів у контейнері.
 * 5) Розробити консольну програму та забезпечити діалоговий
 *  режим роботи з користувачем для демонстрації та тестування рішення.
 *
 */
public class MainClass {

    /**
     * Главный вызывающийся метод
     * @param args массив принимаемы аргументов
     * */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string;
        MyArrayList<String> mlist = new MyArrayList<>();

        System.out.println("Пожалуйста введите строки(для завешения введите _st)");
        while (true) {
            string = (in.nextLine());
            if ((string).equals("_st")) {
                System.out.println("Завершение ввода");
                break;
            } else mlist.add(string);
        }
        if (mlist.size() == 0) {
            System.out.println("Данные не введены!!!");
            return;
        }

        RubezhinClass work = new RubezhinClass(mlist);

        int []mArray = work.raspredelenieTextData();// вызов ф-ции распределения строк
        String []mArrayOfSmallText = work.findMinTextsFromTextArray();// вызов ф-ции нахождения минимальных строк в группах
        /* Вывод результа на консоль */
        System.out.println("Самые маленькие строки");
        for (int i = 0; i < mArray.length; i++) {
            if(mArray[i] != 0){
                System.out.println(mArrayOfSmallText[i] + " = " + mArrayOfSmallText[i].length() + " длинна строки");
            }
        }
    }
}

