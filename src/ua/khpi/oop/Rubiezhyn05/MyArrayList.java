package ua.khpi.oop.Rubiezhyn05;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Класс контейнер созданый для обработки типов данных
 * @author Evgeniy Rubezhin
 * @version 1.0
 * */
public class MyArrayList<T> implements Serializable,Collection<T> {
    private T[] array; // массив элементов
    private int mSize; // размер массива
    private int cursor; // курсор

    public MyArrayList() {
        array = (T[]) new Object[0];
        cursor = -1;
    }

    /**
     * Метод сортировки
     * вызывает метод sort класса Array
     * */
    public void sort() {
        if (array == null) return;
        Arrays.sort(array);
    }

    /**
     * Метод оброботки возврата строки
     * используется стрингбилдер для заполнения строки(для скорости)
     * элементы закидуются в object и там по условию уже смотрит куда вставлять
     *
     * */
    public String toString(){
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
     * возвращаем элемент по индексу
     *
     * @param index индекс для получение элемента
     * */
    public Object get(int index){
        if(index >= 0 && index < mSize)
            return array[index];
        return null;
    }

    /**
     * передаем значение количества элементов
     * */
    @Override
    public int size() {
        return mSize;
    }

    /**
     * проверяем на наличие элемента в контейнере
     *
     * @param o объект для сверки
     * */
    @Override
    public boolean contains(Object o) {
        if (array == null) return false;
        for (int i = 0; i < mSize; i++) {
            if (o.equals(array[i])) return true;
        }
        return false;
    }

    /**
     * отдаем массив с элементами
     * */
    @Override
    public Object[] toArray() {
        return array;
    }

    /**
     * запись элемента в контейнер-массив
     * проход по циклу для записи во временный контейнер
     * потом передаем ссылку на главный массив
     *
     * @param obj объект для добавления
     * */
    @Override
    public boolean add(T obj) {
        if(obj == null || array == null) return false;
        T[] newArrayOfElems = (T[]) new Object[++mSize];
        for (int i = 0; i < mSize; i++) {
            if (i == mSize-1)
                newArrayOfElems[i] = obj;
            else
                newArrayOfElems[i] = array[i];
        }
        array = newArrayOfElems;
        return true;
    }

    /**
     * удаление произвольного элемента из контейнера
     * проход по циклу для сравнения элементов, если есть совпадение
     * то пропусскаем запись, иначе записываем элемент
     *
     * @param o объект для удаления
     * */
    @Override
    public boolean remove(Object o) {
        if(o == null || array == null) return false;
        boolean flagForExeption = true;
        T[] newArrayOfElems = (T[]) new Object[mSize-1];
        int j = 0;
        for (int i = 0; i < mSize; i++) {
            if (o.equals(array[i])){
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
     * проверяем все элементы с полученым контейнером в аргументах
     *
     * @param c контейнер для полной проверки
     * */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (array == null || c == null) return false;
        if (mSize != c.size()) return false;
        Iterator<?> it = c.iterator();
        for (int i = 0; i < mSize; i++) {
            if (!(it.next()).equals(array[i])) return false;
        }
        return true;
    }

    /**
     * очистка всего массива путем обнуления ссылки
     * */
    @Override
    public void clear() {
        array = null;
    }

    /**
     * метод возвращающий созданый класс итератор
     * */
    public Iterator<T> iterator(){
        return new MyIterable().iterator();
    }

    /**
     * класс итератор для работы с циклом for-each
     * */
    public class MyIterable implements Iterable<T> {

        @Override
        public Iterator<T> iterator() {
            return new mIterator();
        }

         class mIterator implements Iterator<T> {

            @Override
            public boolean hasNext() {
                int elem = cursor + 1;
                if (elem == mSize) {
                    cursor = -1;
                    return false;
                } else
                    return (array[elem] != null);
            }

            @Override
            public T next() {
                if (array == null)
                    return null;
                return array[++cursor];
            }

            @Override
            public void remove() {
                if (array == null || cursor == -1 || cursor > mSize) return;
                try{
                    MyArrayList.this.remove(cursor);
                    --cursor;
                }catch (IndexOutOfBoundsException ex){
                    ex.printStackTrace();
                }
            }
        }


    }

    /** Внизу не использующиеся классы
     * -------------------------------------------------------------------------------------
     * Они являются заглушками
     * **/

    /**заглушка*/
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    /**заглушка*/
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**заглушка*/
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    /**заглушка*/
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**заглушка*/
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}
