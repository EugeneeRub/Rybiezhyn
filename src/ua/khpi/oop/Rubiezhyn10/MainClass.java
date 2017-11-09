package ua.khpi.oop.Rubiezhyn10;

import other.Strok;
import ua.khpi.oop.Rybiezhyn07.ConsoleWork;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;
import ua.khpi.oop.Rybiezhyn09.LinkConteiner;
import ua.khpi.oop.Rybiezhyn09.Main;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main class that start the work with classes
 *
 * @author Rubezhin Evgeniy
 * Data 09.11.2017
 * */
public class MainClass {
    private static LinkConteiner<PrisonerInfo> conteiner = null;// container of objects
    private static String arrayOfCommand[] = {"-seeData", "-addData", "-removeData", "-stop", "-serializeData",
            "-deserializeData", "-xmlEncode", "-xmlDecode", "-createContein", "-toString",
            "-toArray", "-contain", "-sort"};// array of command

    /**
     * Start method
     * work with do-while loop where situated main work
     * if you shoose command work, it checked on null object
     * if choose '-auto' command, program will be
     *
     * @param args - argument that will be send from console
     */
    public static void main(String[] args) {
        ConsoleWork work = new ConsoleWork();// class for working with console
        boolean mAutoFlag = false; // flag for single/auto work
        boolean flag = true;// flag for stop method
        Object[] array = null;// array of covered conteiner
        Scanner in;// read data fom console

        if (args.length != 0) {
            if (args[0].equals("-auto"))
                mAutoFlag = true;
        }
        do {
            String n;
            if (!mAutoFlag) {
                System.out.println("Please, input the command(input \'-help\' for help)");
                in = new Scanner(System.in);
                n = in.next();
            }else
                n = arrayOfCommand[(int) (0 + Math.random()*arrayOfCommand.length)];
            switch (n) {
                case "-seeData":
                    System.out.println("------SeeData------");
                    Main.printData(conteiner);
                    break;
                case "-addData":
                    System.out.println("------AddData------");
                    if (!mAutoFlag)
                        conteiner = Main.addData(conteiner);
                    else {
                        if (conteiner == null)
                            conteiner = new LinkConteiner<>();
                        conteiner.add(getPrisonerRandom());
                    }

                    break;
                case "-removeData":
                    System.out.println("------RemoveData------");
                    if (!mAutoFlag)
                        conteiner = Main.removeData(conteiner);
                    else
                        conteiner = removeDataWithFlag(conteiner);
                    break;
                case "-serializeData":
                    System.out.println("------SerializeData------");
                    if (conteiner != null && conteiner.size() != 0)
                        Main.serializeData(getWaySerializeble(mAutoFlag), conteiner);
                    break;
                case "-deserializeData":
                    System.out.println("------DeserializeData------");
                    conteiner = Main.deserializeData(getWaySerializeble(mAutoFlag));
                    break;
                case "-xmlEncode":
                    System.out.println("------XmlEncode------");
                    if (conteiner != null && conteiner.size() != 0)
                    Main.encodeData(getWayEncode(mAutoFlag), conteiner);
                    break;
                case "-xmlDecode":
                    System.out.println("------XmlDecode------");
                    conteiner = Main.decodeData(getWayEncode(mAutoFlag));
                    break;
                case "-createContein":
                    System.out.println("------CreateContein------");
                    conteiner = Main.createContein(conteiner);
                    break;
                case "-toString":
                    System.out.println("------ToString------");
                    if (conteiner != null && conteiner.size() != 0)
                        System.out.println(conteiner.toString());
                    else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-toArray":
                    System.out.println("------ToArray------");
                    if (conteiner != null && conteiner.size() != 0)
                        array = conteiner.toArray();
                    break;
                case "-contain":
                    System.out.println("------Contain------");
                    if (conteiner != null && conteiner.size() != 0) {
                        if (!mAutoFlag){
                            if (conteiner.contains(work.addPrisonerFromConsole()))
                                System.out.println("Have same objects");
                            else
                                System.out.println("Don`t have same objects");
                        }else{
                            if (conteiner.contains(getPrisonerRandom()))
                                System.out.println("Have same objects");
                            else
                                System.out.println("Don`t have same objects");
                        }
                    } else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-containAll":
                    System.out.println("------ContainAll------");
                    if (conteiner != null && conteiner.size() != 0) {
                        if (!mAutoFlag)
                            conteiner.containsAll(Main.createListOfNewElements());
                        else
                            conteiner.containsAll(createListOfNewElementsFromFile(true));
                    } else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-auto":
                    System.out.println("------Auto------");
                    if (mAutoFlag) {
                        System.out.println("Changed to single work");
                        mAutoFlag = false;
                    } else {
                        System.out.println("Changed to auto work");
                        mAutoFlag = true;
                    }
                    break;
                case "-sort":
                    System.out.println("------Sort------");
                    if (conteiner != null && conteiner.size() != 0)
                        chooseSort(mAutoFlag);
                    else
                        System.out.println("Container is null");
                    break;
                case "-help":
                    System.out.println("------Help------");
                    Main.help();
                    dopHelp();
                    break;
                case "-stop":
                    System.out.println("------Stop------");
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command!!!");
                    System.out.println("Try again");
                    break;
            }
        } while (flag);
    }

    /**
     * createListOfNewElementsFromFile method.
     * Method get new list from serializeble data
     * then return it.
     *
     * @param mAutoFlag flag for method that get way from static key
     * @return Collection - created collection from file
     * */
    private static Collection<PrisonerInfo> createListOfNewElementsFromFile(boolean mAutoFlag) {
        LinkConteiner<PrisonerInfo> list;
        list = Main.deserializeData(getWaySerializeble(mAutoFlag));
        return list;
    }

    /**
     * getWaySerializeble method.
     * Method return the way from console or from static key
     *
     * @param mAutoFlag flag that point us about single-work or auto-work
     * @return String - way to file
     * */
    private static String getWaySerializeble(boolean mAutoFlag){
        String str;
        Scanner in;
        if (!mAutoFlag) {
            in = new Scanner(System.in);
            System.out.println("Please input the way for file");
            str = in.next();
        } else {
            str = "D:\\autogenerate.dat";
        }
        return str;
    }

    /**
     * getWayEncode method
     *
     * Method return the way from console or from static key
     *
     * @param mAutoFlag flag that point us about single-work or auto-work
     * @return String - way to file
     * */
    private static String getWayEncode(boolean mAutoFlag){
        String str;
        Scanner in;
        if (!mAutoFlag) {
            in = new Scanner(System.in);
            System.out.println("Please input the way for file(for \'.xml\' file)");
            str = in.next();
        } else {
            str = "D:\\autogenerate.xml";
        }
        return str;
    }

    /**
     * removeDataWithFlag method.
     * Method return the way from console or from static key
     *
     * @param conteiner list that will be update by deleting object
     * @return LinkConteiner - list that was update
     * */
    private static LinkConteiner<PrisonerInfo> removeDataWithFlag(LinkConteiner<PrisonerInfo> conteiner) {
        if (conteiner != null) {
            if (!(conteiner.size() == 0)){
                conteiner.remove((int)(0 + Math.random() * (conteiner.size()-1)));
            }else
                System.out.println("Container is null or has 0 elements");
        }else
            System.out.println("Container is null");
        return conteiner;
    }

    /**
     * chooseSort method.
     * Method that sort list by choosing the way of sorting
     * and way of sorting
     *
     * @param flag point us about single/auto work
     * */
    private static void chooseSort(boolean flag) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        Expression ex = null;
        System.out.println("Please, choose the variant of sort");
        System.out.println("1 - sort by name");
        System.out.println("2 - sort by dateOfBirth");
        System.out.println("3 - sort by goToJail");
        System.out.println("4 - sort by goFromJail");
        if (!flag)
            n = in.nextInt();
        else
            n = (int)(1 + Math.random() * 3);
        switch (n) {
            case 1:
                ex = ExpressionHelper::useMSNEquals;
                break;
            case 2:
                ex = ExpressionHelper::useBirthdayEquals;
                break;
            case 3:
                ex = ExpressionHelper::useGoToJailEquals;
                break;
            case 4:
                ex = ExpressionHelper::useGoFromJailEquals;
                break;
        }
        boolean flagForChoose = false;
        System.out.println("Please, choose the side of sorting method");
        System.out.println("By default sorting will be from min to max");
        System.out.println("1 - from max to min");
        System.out.println("2 - from min to max");
        if (!flag)
            n = in.nextInt();
        else
            n = (int)(1 + Math.random() * 1);
        switch (n) {
            case 1:
                flagForChoose = true;
                break;
            case 2:
                flagForChoose = false;
                break;
        }
        sort(conteiner, ex, flagForChoose);
    }

    /**
     * sort method.
     * Method that sort container by special function
     *
     * @param conteiner list of objects
     * @param ex address of function that will be called in sorting time
     * @param flagForChoose point us about single/auto work
     * */
    private static <K extends PrisonerInfo> LinkConteiner<K> sort(LinkConteiner<K> conteiner,
                                                                 Expression ex, boolean flagForChoose) {
        LinkConteiner.Node firstwalker = conteiner.getNode();
        while (firstwalker.hasNext()) {
            LinkConteiner.Node secondWalker = firstwalker.getNext();
            while (secondWalker != null) {
                if (ex.isEqual(firstwalker.getItem(), secondWalker.getItem(), flagForChoose)) {
                    LinkConteiner.Node middle = conteiner.newInstance();
                    middle.setItem(firstwalker.getItem());
                    firstwalker.setItem(secondWalker.getItem());
                    secondWalker.setItem(middle.getItem());
                }
                secondWalker = secondWalker.getNext();
            }
            firstwalker = firstwalker.getNext();
        }
        return conteiner;
    }

    /**
     * dopHelp method.
     * Method that add some string in console when will be called
     * the Main.help() method
     * */
    private static void dopHelp() {
        System.out.println("Команда -sort - для сортировки");
        System.out.println("Команда -auto - включает авто работу");
    }

    /**
     * getPrisonerRandom method.
     * Method get list from serializeble file and when call get mthod in list,
     * by random return the PrisonerInfo object
     *
     * @return PrisonerInfo - random object from list
     * */
    private static PrisonerInfo getPrisonerRandom() {
        PrisonerInfo info = null;
        LinkConteiner<PrisonerInfo> list = new LinkConteiner<>();
        list = Main.deserializeData(getWaySerializeble(true));
        int index = (int) (0 + Math.random() * (list.size()-1));
        return list.get(index);
    }

}

