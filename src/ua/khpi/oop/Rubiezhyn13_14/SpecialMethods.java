package ua.khpi.oop.Rubiezhyn13_14;

import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.util.ArrayList;

/**
 * Class with methods for work with list
 *
 * @author Rubezhin Evgeniy
 * Data 05.12.2017
 * */
class SpecialMethods{
    private ArrayList<PrisonerInfo> list;// list with prisoners

    public SpecialMethods(ArrayList<PrisonerInfo> list){
        this.list = list;
    }

    /**
     * Method that search minimal element in list
     * and then print it
     * */
    public synchronized void findMinElement(Thread t, int timemls){
        PrisonerInfo prisoner = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {

                try {
                    Thread.sleep(timemls);
                } catch (InterruptedException e) {
                    t.interrupt();
                }

                if (!Thread.interrupted()) {
                    PrisonerInfo prisoner2 = list.get(j);
                    if (prisoner.getPerson().getMGrowth() > prisoner2.getPerson().getMGrowth()) {
                        prisoner = prisoner2;
                    }
                } else{
                    System.out.println("Сработала экстренная остановка!!!");
                    System.out.println("Вывод текущего результата при работе");
                    ConsoleWork.printPrisoner(prisoner);
                    return;
                }

            }
        }
        System.out.println("Минимальный элемент у пользователя");
        ConsoleWork.printPrisoner(prisoner);
    }

    /**
     * Method that search maximal element in list
     * and then print it
     * */
    public synchronized void findMaxElement(Thread t, int timemls){
        PrisonerInfo prisoner = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                try {
                    Thread.sleep(timemls);
                } catch (InterruptedException e) {
                    t.interrupt();
                }
                if (!Thread.interrupted()){
                    PrisonerInfo prisoner2 = list.get(j);
                    if (prisoner.getPerson().getMGrowth() < prisoner2.getPerson().getMGrowth()){
                        prisoner = prisoner2;
                    }
                } else {
                    System.out.println("Сработала экстренная остановка!!!");
                    System.out.println("Вывод текущего результата при работе");
                    ConsoleWork.printPrisoner(prisoner);
                    return;
                }
            }
        }
        System.out.println("Максимальный элемент у пользователя");
        ConsoleWork.printPrisoner(prisoner);
    }

    /**
     * Method that count sum and then divide on count of elements
     * in list, next step after all of that it is a printing result
     * */
    public synchronized void printMiddleResult(Thread t, int timemls){

        int countOfElements = list.size();
        float sum = 0;
        for (PrisonerInfo prisoner : list) {
            try {
                Thread.sleep(timemls);
            } catch (InterruptedException e) {
                t.interrupt();
            }
            if (!Thread.interrupted())
                sum += prisoner.getPerson().getMGrowth();
            else {
                System.out.println("Сработала экстренная остановка!!!");
                System.out.println("Результат текущего вычисления sum = " + sum);
                return;
            }
        }
        System.out.println("Текущее среднее значение равно res = " + (sum/countOfElements));
    }

    /**
     * Method that count sum elements in list,
     * next step, after all of that it is a printing result
     * */
    public synchronized void printSumOfElements(Thread t, int timemls){
        float sum = 0;
        for (PrisonerInfo prisoner : list) {
            try {
                Thread.sleep(timemls);
            } catch (InterruptedException e) {
                t.interrupt();
            }
            if (!Thread.interrupted())
                sum += prisoner.getPerson().getMGrowth();
            else {
                System.out.println("Сработала экстренная остановка!!!");
                System.out.println("Результат текущего вычисления sum = " + sum);
                return;
            }
        }
        System.out.println("Текущая сумма равна res = " + sum);
    }

    /**
     * Method that count counting elements in list by special category,
     * then print count of elements
     * */
    public synchronized void countingOfElements(Thread t, int timemls){
        int counter = 0;
        for (PrisonerInfo prisoner : list) {
            try {
                Thread.sleep(timemls);
            } catch (InterruptedException e) {
                t.interrupt();
            }
            if (!Thread.interrupted()) {
                String colorOfEyes = prisoner.getPerson().getMColorEyes();
                if (colorOfEyes.matches("[bB]lue|[gG]reen|[Гг]олубой|[Кк]арий"))
                    counter++;
            } else {
                System.out.println("Сработала экстренная остановка!!!");
                System.out.println("Результат текущего вычисления counter = " + counter);
                return;
            }
        }
        System.out.println("За критерием color eyes найдено " + counter + " совпадений");
    }

    /**
     * Method that count counting elements in list by special category,
     * then print elements from list
     * */
    public synchronized void printGoodElements(Thread t, int timemls){
        System.out.println("Результат выполнения ф-ции и вывод данных");
        for (PrisonerInfo prisoner : list) {
            try {
                Thread.sleep(timemls);
            } catch (InterruptedException e) {
                t.interrupt();
            }
            if (!Thread.interrupted()){
                String colorOfEyes = prisoner.getPerson().getMColorEyes();
                if (colorOfEyes.matches("[bB]lue|[gG]reen"))
                    ConsoleWork.printPrisoner(prisoner);
            }else {
                System.out.println("Сработала экстренная остановка!!!");
                return;
            }
        }
    }
}
