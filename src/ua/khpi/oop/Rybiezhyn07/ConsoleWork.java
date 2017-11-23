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

        System.out.println("Input the information about prisoner");

        //get info from console about person
        {
            System.out.println("Name, surname and middlename (can input the prisoner name)");
            person.setMPIB(in.nextLine());

            System.out.print("Date of birth: ");
            person.setMDateOfBirthd(in.next());

            System.out.print("Growth: ");
            person.setMGrowth(in.nextFloat());

            System.out.print("Color of eyes: ");
            person.setMColorEyes(in.next());
        }
        prisoner.setPerson(person);

        System.out.print("When go to jail: ");
        prisoner.setMDateOfGoToJail(in.next());

        System.out.print("When go from jail: ");
        prisoner.setMDateOfGoFromJail(in.next());

        System.out.println("Special signs(\"-stop\" for stop adding):");
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
        CorrectPrint print = new CorrectPrint(new int[]{35,20,11,16,22});
        System.out.printf("%s%27s%11s%16s%22s%24s\n","ИФО (или кличка)","День рождения","Рост","Цвет глаз","Дата заключения",
                "Дата освобождения");
        print.printLine(person.getMPIB());
        print.printLine(person.getMDateOfBirthd());
        print.printLine(person.getMGrowth());
        print.printLine(person.getMColorEyes());
        print.printLine(prisoner.getMDateOfGoToJail());
        print.printLine(prisoner.getMDateOfGoFromJail());
        System.out.println();
        LinkedList<String> list = prisoner.getMListOfSpecialSigns();
        System.out.println("Особые примечания:");
        for (int j = 0; j < list.size(); j++) {
            System.out.println(j + ") " + list.get(j));
        }
    }
}
