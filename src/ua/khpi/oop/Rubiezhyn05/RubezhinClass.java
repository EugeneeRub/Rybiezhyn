package ua.khpi.oop.Rubiezhyn05;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Класс для запуска работы контейнера и обработки его данных
 * @version 1.0
 * */
class RubezhinClass {
     private Collection<String> list;// свой контейнер для сохранения строк
     private Scanner in = new Scanner(System.in); // читаем из командной строки
     private String[][] mArrayOfStrings; // двумерный массив для работы с данными
     private StringBuilder string; // хранение строк
     private String[] mArrayOfSmallText = new String[3];// масив для хранения последнего результата
     private int[] counters = {0, 0, 0}; // счетчики элементов в двумерном массиве

    RubezhinClass(Collection<String> _list){
        list = _list;
    }

    /**
     * Проход по ArrayList и получение строки в StringBuilder
     * Проверка по первому символу и запись в нужный участок
     **/
    public int[] raspredelenieTextData() {
        mArrayOfStrings = new String[3][list.size()];// инициализируем массив для хранения строк по категориям

        for (Iterator<String> it = list.iterator();it.hasNext();) {
            string = new StringBuilder(it.next());
            char ch = string.charAt(0);
            /* Проверка Гласных букв латиницы */
            if (ch == 65 || ch == 97
                    || ch == 69 || ch == 101
                    || ch == 73 || ch == 105
                    || ch == 79 || ch == 111
                    || ch == 85 || ch == 117) {

                mArrayOfStrings[0][counters[0]] = string.toString();
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
                mArrayOfStrings[1][counters[1]] = string.toString(); // запись по счетчику
                counters[1]++; // увеличение счетчика
            }
            /* Остальные символы забрасываются по умолчанию */
            else {
                mArrayOfStrings[2][counters[2]] = string.toString();
                counters[2]++;
            }
        }
        return counters;
    }

    /**
     * Проход по циклу для нахождения самой мальнекой стоки из всех в своем ряде
     */
    public String[] findMinTextsFromTextArray() {
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
        return mArrayOfSmallText;
    }
}
