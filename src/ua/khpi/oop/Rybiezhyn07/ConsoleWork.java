package ua.khpi.oop.Rybiezhyn07;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class created for console work,
 * use print and get methods for work with array
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class ConsoleWork {
    private static int arr[] = {30,20,11,16,22};// array of width of objects
    private static int index = 0;// index that say that number we must take from arr[]
    private static String str;// string for get size and decrement lenght
    private static boolean flag = true;// flag for first element

    /**
     * Method that print in line words that are equals by width
     *
     * @param elem object that will be input in line
     * */
    public void printLine(Object elem) {
        if (index == 5) {
            index = 0;
            flag = true;
        }
        if (flag) {
            System.out.print(elem);
            str = elem.toString();
            flag = false;
        } else {
            int len = arr[index] - str.length();
            for (int i = 0; i < len; i++) {
                System.out.print(" ");
            }
            str = elem.toString();
            System.out.print(elem);
            index++;
        }
    }

    /**
     * Method tha print all prisoners from array
     *
     * @param cardBoard card board that contain array of prisoners
     * */
    public void printArray(PoliceCardBoard cardBoard){
        PrisonerInfo[] prisoner = cardBoard.getArrayOfPrisoner();

        if (prisoner == null){
            System.out.println("Array empty");
            return;
        }

        if (prisoner.length != 0) {
            for (int i = 0; i < prisoner.length; i++) {
                printPrisoner(prisoner[i]);
            }
        }else {
            System.out.println("Nothing to show");
        }
    }

    /**
     * Method load in object information from console
     *
     * @return prisoner object that will be fill and return
     * */
    public PrisonerInfo addPrisonerFromConsole(){
        Scanner in = new Scanner(System.in);
        PrisonerInfo prisoner = new PrisonerInfo();
        Person person = new Person();
        boolean flag = true;
        LinkedList<String> list = new LinkedList<>();

        System.out.println("Введите данные о заключенном");

        //get info from console about person
        {
            System.out.print("Имя, Фамилия и Отчество заключенного (можно кличку): ");
            person.setMPIB(in.nextLine());

            System.out.print("Дата рождения: ");
            person.setMDateOfBirthd(in.next());

            System.out.print("Рост: ");
            person.setMGrowth(in.nextFloat());

            System.out.print("Цвет глаз: ");
            person.setMColorEyes(in.next());
        }
        prisoner.setPerson(person);

        System.out.print("Дата заключения: ");
        prisoner.setMDateOfGoToJail(in.next());

        System.out.print("Дата освобождения: ");
        prisoner.setMDateOfGoFromJail(in.next());

        System.out.println("Особые примечания(\"-stop\" для остановки записи):");
        Scanner in2 = new Scanner(System.in);
        do {
            String text = in2.nextLine();
            if(text.equals("-stop")){
                flag = false;
            }else
                list.add(text);
        } while (flag);
        prisoner.setMListOfSpecialSigns(list);

        return prisoner;
    }

    /**
     * Method print only one prisoner in console
     *
     * @param prisoner object that will be print
     * */
    public void printPrisoner(PrisonerInfo prisoner){
        Person person = prisoner.getPerson();
        System.out.printf("%s%27s%11s%16s%22s%24s\n","ИФО (или кличка)","День рождения","Рост","Цвет глаз","Дата заключения",
                "Дата освобождения");
        printLine(person.getMPIB());
        printLine(person.getMDateOfBirthd());
        printLine(person.getMGrowth());
        printLine(person.getMColorEyes());
        printLine(prisoner.getMDateOfGoToJail());
        printLine(prisoner.getMDateOfGoFromJail());
        System.out.println();
        LinkedList<String> list = prisoner.getMListOfSpecialSigns();
        System.out.println("Особые примечания:");
        for (int j = 0; j < list.size(); j++) {
            System.out.println(j + ") " + list.get(j));
        }
    }
}