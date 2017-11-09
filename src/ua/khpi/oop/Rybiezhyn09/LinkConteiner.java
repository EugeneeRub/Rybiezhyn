package ua.khpi.oop.Rybiezhyn09;

import ua.khpi.oop.Rybiezhyn07.Person;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class LinkConteiner<Type extends PrisonerInfo> implements Serializable,Collection<Type> {
    private int mSize;
    private Node<Type> prisoner;

    public LinkConteiner(){
        mSize = 0;
        prisoner = null ;
    }

    public Node<Type> getNode(){
        return prisoner;
    }

    public Node newInstance(){
        return new Node();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<Type> walker = prisoner;
        builder.append("[");
        while (walker != null){
            builder.append((walker.item).getPerson().getMPIB()).append(", ");
            builder.append((walker.item).getPerson().getMDateOfBirthd()).append(", ");
            builder.append((walker.item).getPerson().getMGrowth()).append(", ");
            builder.append((walker.item).getPerson().getMColorEyes()).append(", ");
            builder.append((walker.item).getMDateOfGoFromJail()).append(", ");
            builder.append((walker.item).getMDateOfGoToJail()).append(", ");
            for (String str : (walker.item).getMListOfSpecialSigns()) {
                builder.append(str).append(", ");
            }
            builder.append("\n");
            walker = walker.next;
        }
        builder.delete(builder.length()-3,builder.length());
        builder.append("]");
        return builder.toString();
    }

    public Type get(int index) {
        if (index < 0 || index >= mSize)
            return null;
        Node<Type> walker = prisoner;
        int counter = 0;
        while (counter != index){
            walker = walker.next;
            counter++;
        }
        return walker.item;
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return (prisoner == null);
    }

    @Override
    public boolean contains(Object o) {
        if (mSize == 0 || prisoner == null || o == null)
            return false;
        Node walker = prisoner;
        PrisonerInfo obj = (PrisonerInfo) o;
        while(walker != null){
            if(walker.equals(obj))
                return true;
            walker = walker.next;
        }
        return false;
    }

    @Override
    public Iterator<Type> iterator() {
        return new myIterable().iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[mSize];
        Node walker = prisoner;
        int mCounter1 = 0;
        while (walker != null){
            array[mCounter1] = walker.item;
            mCounter1++;
            walker = walker.next;
        }
        return array;
    }

    @Override
    public boolean add(Type element) {
        if (prisoner == null){
            prisoner = new Node<>();
            prisoner.item = element;
            mSize++;
            return true;
        }
        Node<Type> walker = prisoner;
        while(walker.hasNext()){
            walker = walker.next;
        }
        Node<Type> type = new Node<>();
        type.item = element;

        walker.next = type;

        mSize++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    public boolean remove(int index){
        if (mSize == 0 || prisoner == null || index < 0 || index > mSize)
            return false;

        Node<Type> walker = prisoner;
        Node<Type> willRemoved;
        int mCounter = 0;
        mSize--;

        if (index == 0){
            willRemoved = walker;
            walker = willRemoved.next;
            return true;
        }

        while (walker != null){
            if (mCounter == index-1){
                willRemoved = walker.next;
                willRemoved.item = null;
                walker.next = willRemoved.next;
                return true;
            }
                walker = walker.next;
            mCounter++;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (mSize == 0 || prisoner == null || c.size() != mSize || c == null)
            return false;

        Node<Type> walker = prisoner;
        Iterator it = c.iterator();
        while(walker != null && it.hasNext()){
            if(!(walker.item).equals(it.next()))
                return false;
            walker = walker.next;
        }
        return true;
    }

    @Override
    public void clear() {
        Node<Type> walker = prisoner;
        while(walker != null){
            walker.item = null;
            walker = walker.next;
        }
        prisoner = null;
    }

    class myIterable implements Iterable<Type>{

        @Override
        public Iterator<Type> iterator() {
            return new mIterator();
        }

         class mIterator implements Iterator<Type>{
            Node<Type> walker;
            int counter = 0;

            public mIterator(){
                walker = prisoner;
                counter = 0;
            }

            @Override
            public boolean hasNext() {
                if (counter == 0) {
                    if (walker != null)
                        return true;
                    return false;
                }
                if (walker.next != null)
                    return true;
                return false;
            }

            @Override
            public Type next() {
                if (counter == 0) {
                    counter++;
                    return walker.item;
                }
                counter++;
                walker = walker.next;
                return walker.item;
            }
         }
    }

    public class Node<Type extends PrisonerInfo> implements Serializable {
        Type item;
        Node<Type> next;

        Node() {
            item = null;
            next = null;
        }


        public Type getItem() {
            return item;
        }

        public void setItem(Type item) {
            this.item = item;
        }

        public Node<Type> getNext() {
            return next;
        }

        public void setNext(Node<Type> next) {
            this.next = next;
        }


        public boolean equals(PrisonerInfo prisonerInfo) {
            if (prisonerInfo == null)
                return false;
            else if (!equals(prisonerInfo.getPerson()))
                return false;
            else if (!(item.getMDateOfGoFromJail()).equals(prisonerInfo.getMDateOfGoFromJail()))
                return false;
            else if (!(item.getMDateOfGoToJail()).equals(prisonerInfo.getMDateOfGoToJail()))
                return false;

            if ((item.getMListOfSpecialSigns()).size() != (prisonerInfo.getMListOfSpecialSigns()).size())
                return false;

            Iterator it1 = (item.getMListOfSpecialSigns()).iterator();
            Iterator it2 = (prisonerInfo.getMListOfSpecialSigns()).iterator();
            while (it1.hasNext() && it2.hasNext()) {
                if (!(it1.next()).equals(it2.next()))
                    return false;
            }
            return true;
        }

        public boolean equals(Person p) {
            Person mPerson = item.getPerson();
            if (p == null)
                return false;
            else if (!(mPerson.getMPIB()).equals(p.getMPIB()))
                return false;
            else if (!(mPerson.getMDateOfBirthd()).equals(p.getMDateOfBirthd()))
                return false;
            else if (!(mPerson.getMColorEyes()).equals(p.getMColorEyes()))
                return false;
            else if (!((mPerson.getMGrowth()) == p.getMGrowth()))
                return false;

            return true;
        }

        public boolean hasNext(){
            return (next != null);
        }

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /**
     * Don`t need to use
     * */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Don`t need to use
     * */
    @Override
    public boolean addAll(Collection<? extends Type> c) {
        return false;
    }

    /**
     * Don`t need to use
     * */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * Don`t need to use
     * */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}
