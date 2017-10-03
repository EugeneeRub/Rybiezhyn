package ua.khpi.oop.Rubiezhyn05;

import java.util.Iterator;
import java.util.Scanner;

public class MainClass {

    public static  void main(String[] args) {
        IndWork work = new IndWork();

        MyArrayList<String> list = new MyArrayList<>();
        list.add("asda");
        list.add("asdassssss");
        list.add("asdaas");
        list.add("asdaasdwqeq");
        Iterator<String> it = list.iterator();
        if (it.hasNext()){
            it.remove();
            it.next();
            it.remove();
        }
        work.getTextFromConsole();// вызов ф-ции получения текста
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

    static private class IndWork {
        static private MyArrayList<String> myList = new MyArrayList<>();
        static private Scanner in = new Scanner(System.in); // читаем из командной строки
        static private String[][] mArrayOfStrings; // двумерный массив для работы с данными
        static private StringBuilder string = new StringBuilder(); // хранение строк
        static private String[] mArrayOfSmallText = new String[3];// масив для хранения последнего результата
        static private int[] counters = {0, 0, 0}; // счетчики элементов в двумерном массиве

        /**
         * Ввод из командной строки
         * Проверка строки, если она равна _st то выход из цикла
         * Строки записываются в ArrayList
         */
        private void getTextFromConsole() {
            System.out.println("Пожалуйста введите строки(для завешения введите _st)");
            while (true) {
                string.append(in.nextLine());
                if ((string.toString()).equals("_st")) {
                    System.out.println("Завершение ввода");
                    break;
                } else myList.add(string.toString());
                string.delete(0, string.length());// очистка StringBuilder перед новой записью
            }

            if (myList.size() == 0) {
                System.out.println("Данные не введены!!!");
                return;
            }
        }

        /**
         * Проход по ArrayList и получение строки в StringBuilder
         * Проверка по первому символу и запись в нужный участок
         **/
        private int[] raspredelenieTextData() {
            mArrayOfStrings = new String[3][myList.size()];// инициализируем массив для хранения строк по категориям

            for (int j = 0; j < myList.size(); j++) {
                string = new StringBuilder(myList.get(j));
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
        private String[] findMinTextsFromTextArray() {
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
}

class MyArrayList<T> {
    private T[] array;
    private int mSize;
    private int currentElement;

    MyArrayList() {
        array = (T[]) new Object[0];
        mSize = 0;
        currentElement = -1;
    }

    /**
     * метод оброботки массива и возврата строки
     * используется стрингбилдер для заполнения строки(для скорости)
     * элементы закидуются в object и там по условию уже смотрит куда вставлять
     *
     * */
    String myToString(){
        if (array == null) return "";
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < mSize; i++) {
            Object obj = array[i];
            if (i == mSize-1){
                builder.append(obj);
            }else {
                builder.append(obj + ", ");
            }

        }
        builder.append("]");
        return builder.toString();
    }
    /**
     * запись элемента в контейнер-массив
     * проход по циклу для записи во временный контейнер
     * потом передаем ссылку на главный массив
     * */
    void add(T obj){
        if(obj == null || array == null) return;
        T[] newArrayOfElems = (T[]) new Object[++mSize];
        for (int i = 0; i < mSize; i++) {
            if (i == mSize-1)
                newArrayOfElems[i] = obj;
            else
                newArrayOfElems[i] = array[i];
        }
        array = newArrayOfElems;
    }
    /**
     * очистка всего массива путем обнуления ссылки
     * */
    void clear(){
        array = null;
    }
    /**
     * удаление произвольного элемента из контейнера
     * проход по циклу для сравнения элементов, если есть совпадение
     * то пропусскаем запись, иначе записываем элемент
     * */
    boolean remove(T obj) {
        if(obj == null || array == null) return false;
        boolean flagForExeption = true;
        T[] newArrayOfElems = (T[]) new Object[mSize-1];
        int j = 0;
        for (int i = 0; i < mSize; i++) {
            if (obj.equals(array[i])){
                flagForExeption = false;
            }else {
                newArrayOfElems[j++] = array[i];
            }
        }
        if (flagForExeption) return false;
        array = newArrayOfElems;
        mSize--;
        return true;
    }
    /**
     * удаление произвольного элемента из контейнера по индексу
     * проход по циклу для сравнения элементов, если есть совпадение
     * то пропусскаем запись, иначе записываем элемент
     * */
    boolean remove(int index) {
        if(index < 0 || index > mSize || array == null) return false;
        boolean flagForExeption = true;
        T[] newArrayOfElems = (T[]) new Object[mSize-1];
        int j = 0;
        for (int i = 0; i < mSize; i++) {
            if (index == i){
                flagForExeption = false;
            }else {
                newArrayOfElems[j++] = array[i];
            }
        }
        if (flagForExeption) return false;
        array = newArrayOfElems;
        mSize--;
        return true;
    }
    /**
     * отдаем массив с элементами
     * */
    Object[] toArray(){
        return array;
    }
    /**
     * передаем значение количества элементов
     * */
    int size(){
        return mSize;
    }
    /**
     * возвращаем элемент по индексу
     * */
    T get(int index){
        if(index >= 0 && index < mSize)
            return array[index];
        return null;
    }
    /**
     * проверяем на наличие элемента в контейнере
     * */
    boolean contains(T obj){
        if (array == null) return false;
        for (int i = 0; i < mSize; i++) {
            if (obj.equals(array[i])) return true;
        }
        return false;
    }
    /**
     * проверяем все элементы с полученым контейнером в аргументах
     * */
    boolean containsAll(MyArrayList<T> argArray) {
        if (array == null || argArray == null) return false;
        if (mSize != argArray.mSize) return false;
        for (int i = 0; i < mSize; i++) {
            if (!(argArray.get(i)).equals(array[i])) return false;
        }
        return true;
    }

    public Iterator<T> iterator(){
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            int elem = currentElement + 1;
            if (elem == mSize){
                currentElement = -1;
                return false;
            } else
                return (array[elem] != null);
        }

        @Override
        public T next() {
            if (array == null) return null;
            return array[++currentElement];
        }

        @Override
        public void remove() {
            if (array == null || currentElement == -1 || currentElement > mSize) return;
            try{
                MyArrayList.this.remove(currentElement);
                --currentElement;
            }catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
            }

        }
    }
}
