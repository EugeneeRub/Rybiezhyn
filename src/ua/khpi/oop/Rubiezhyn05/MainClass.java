package ua.khpi.oop.Rubiezhyn05;

import java.util.Collection;
import java.util.List;
import ua.khpi.oop.Rubiezhyn05.MyArrayList;

/**
 * @author  Евгений Рубежин
 * Группа: КИТ-16а
 * Вимоги
 *
 * Розробити клас-контейнер, що ітерується для збереження початкових даних завдання л.р. №3 у вигляді масиву рядків з можливістю додавання, видалення і зміни елементів.
 * В контейнері реалізувати та продемонструвати наступні методи:
 *
 * String toString() повертає вміст контейнера у вигляді рядка;
 * void add(String string) додає вказаний елемент до кінця контейнеру;
 * void clear() видаляє всі елементи з контейнеру;
 * boolean remove(String string) видаляє перший випадок вказаного елемента з контейнера;
 * Object[] toArray() повертає масив, що містить всі елементи у контейнері;
 * int size() повертає кількість елементів у контейнері;
 * boolean contains(String string) повертає true, якщо контейнер містить вказаний елемент;
 * boolean containsAll(Container container) повертає true, якщо контейнер містить всі елементи з зазначеного у параметрах;
 * public Iterator<String> iterator() повертає ітератор відповідно до Interface Iterable.
 *
 * В класі ітератора відповідно до Interface Iterator реалізувати методи:
 * public boolean hasNext();
 * public String next();
 * public void remove().
 * Продемонструвати роботу ітератора за допомогою циклів while и for each.
 *
 * Забороняється використання контейнерів (колекцій) і алгоритмів з Java Collections Framework.
 */
public class MainClass {

    /**
     * Главный вызывающийся метод
     * @param args массив принимаемы аргументов
     * */
    public static  void main(String[] args) {

        MyArrayList<String> mlist = new MyArrayList<>();

        //System.out.println(mlist.toString());
        //RubezhinClass work = new RubezhinClass(mlist);
        //work.getTextFromConsole();// вызов ф-ции получения текста
        //int []mArray = work.raspredelenieTextData();// вызов ф-ции распределения строк
        //String []mArrayOfSmallText = work.findMinTextsFromTextArray();// вызов ф-ции нахождения минимальных строк в группах
        ///* Вывод результа на консоль */
        //System.out.println("Самые маленькие строки");
        //for (int i = 0; i < mArray.length; i++) {
        //    if(mArray[i] != 0){
        //        System.out.println(mArrayOfSmallText[i] + " = " + mArrayOfSmallText[i].length() + " длинна строки");
        //    }
        //}

    }
}

