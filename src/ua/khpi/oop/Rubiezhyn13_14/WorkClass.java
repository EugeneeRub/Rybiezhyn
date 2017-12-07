package ua.khpi.oop.Rubiezhyn13_14;

import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.CorrectPrint;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that has work with new threads
 *
 * @author Rubezhin Evgeniy
 * Data 05.12.2017
 * */
public class WorkClass {
    private ArrayList<PrisonerInfo> list;// list of prisoners
    public static ConcurrentHashMap<String,Double> map = new ConcurrentHashMap<>();
    private double resultTime;// result of time work in current thread
    private mySpecialThread threads[] = new mySpecialThread[3];// all threads that will be created

    /**
     * Constructor
     * @param list of prisoners
     * */
    public WorkClass(ArrayList<PrisonerInfo> list){
        this.list = list;
    }

    /**
     * Method that start work with threads
     * and control their life or stop program
     * */
    public void work() {
        int commands[] = new int[3];// commands for threads
        boolean flag = true;// flag for stop the program
        boolean threadFlag = false;
        int timeSleep = 0;// time sleeping for this thread
        int timeSleepForNThread = 0;// time sleep for other threads
        Scanner in = new Scanner(System.in);// reader from console

        do {
            System.out.println("Input the command");
            switch (in.next()) {
                case "-work":
                    commands = new int[3];
                    System.out.println("Введите время сна главного потока в млс");
                    timeSleep = in.nextInt();
                    System.out.println("Введите время сна побочных потоков в млс(Будет домножено)");
                    timeSleepForNThread = in.nextInt();
                    help();
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Выбирите " + (i + 1) + " метод для работы потока №" + (i + 1));
                        commands[i] = in.nextInt();
                    }
                    threadFlag = true;
                    break;
                case "-print":
                    for (PrisonerInfo prisonerInfo : list){
                        ConsoleWork.printPrisoner(prisonerInfo);
                    }
                    break;
                case "-stop":
                    flag = false;
                default:
                    threadFlag = false;
            }
            if (threadFlag){
                for (int i = 0; i < 3; i++) {
                    threads[i] = new mySpecialThread(commands[i],timeSleepForNThread,new SpecialMethods(list));
                }
                for (int i = 0; i < 3; i++) {
                    threads[i].setName("Thread #" + (i+1));
                    threads[i].setT(threads[i]);
                }
                for (int i = 0; i < 3; i++) {
                    threads[i].start();
                }

                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 3; i++) {
                    if(threads[i].isAlive()){
                        threads[i].interrupt();
                    }
                }

                System.out.println("Запуск последовательной обработки");
                startWorkInCurrThread(commands,timeSleepForNThread);
                System.out.printf("\n%s%22s%28s\n","Поток","Время сна(mls)","Время выполнения(sec)");
                CorrectPrint print = new CorrectPrint(new int[]{18,21});
                Class classs = Main.class;
                print.printLine(classs.getSimpleName());
                print.printLine(timeSleepForNThread);
                print.printLine(resultTime);
                System.out.println();

                double sum = 0;
                for (Map.Entry<String,Double> entry : map.entrySet()) {
                    print = new CorrectPrint(new int[]{18,21});
                    print.printLine(entry.getKey());
                    print.printLine(timeSleepForNThread);
                    print.printLine(entry.getValue());
                    sum += entry.getValue();
                    System.out.println();
                }
                System.out.println("Скорость паралельной работы больше последовательной в " + sum/map.size() + " раза");

            }
        } while (flag);
    }

    /**
     * Mehtod for consistently work for compare the threads
     *
     * @param commands will activate the methods
     * @param t time for sleep
     * */
    private void startWorkInCurrThread(int[] commands,int t){
        long timeCurentThread  = System.nanoTime();
        SpecialMethods spec = new SpecialMethods(list);
        System.out.println("Работа последовательной обработки");
        for (int command : commands){
            switch (command){
                case 1:
                    spec.findMinElement(new Thread(),t);
                    break;
                case 2:
                    spec.findMaxElement(new Thread(),t);
                    break;
                case 3:
                    spec.printMiddleResult(new Thread(),t);
                    break;
                case 4:
                    spec.printSumOfElements(new Thread(),t);
                    break;
                case 5:
                    spec.countingOfElements(new Thread(),t);
                    break;
                case 6:
                    spec.printGoodElements(new Thread(),t);
                    break;
                default:
                    System.out.println("Неверна команда или нет команды");
            }
        }
        resultTime = (System.nanoTime() - timeCurentThread)/1000000000d;
    }

    /**
     * Method that print on console command that has this program
     * */
    private void help() {
        System.out.println("Поиск минимального элемента в списке, нажмите \"1\"");
        System.out.println("Поиск максимального элемента в списке, нажмите \"2\"");
        System.out.println("Подсчет среденего значения списка, нажмите \"3\"");
        System.out.println("Подсчет суммы значений списка, нажмите \"4\"");
        System.out.println("Подсчет количества значений списка по критерию, нажмите \"5\"");
        System.out.println("Вывод значений списка по критерию, нажмите \"6\"");
    }

}

