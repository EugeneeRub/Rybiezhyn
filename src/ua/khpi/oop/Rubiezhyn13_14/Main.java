package ua.khpi.oop.Rubiezhyn13_14;

import ua.khpi.oop.Rubiezhyn05.MyArrayList;
import ua.khpi.oop.Rubiezhyn11_12.*;
import ua.khpi.oop.Rybiezhyn07.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Main class that start work
 *
 * @author Rubezhin Evgeniy
 * Data 03.12.2017
 * */
public class Main {
    private static ArrayList<PrisonerInfo> list;// list of prisoners

    public static void main(String[] args) {
        createList();// call this method to load data from .json file
        WorkClass workClass = new WorkClass(list);
        workClass.work();
    }

    /**
     * Method that create new list from file
     * this method call special reader that get
     * information from .json file
     * */
    private static void createList(){
        try {
            MyArrayList<PrisonerInfo> _list = ReadFromWithRegex.readFromFile(new File("D:\\prisonerscopy.json"));
            list = new ArrayList<>();
            if (_list != null)
                list.addAll(_list);
        } catch (NotMatchedDataExeption exeption) {
            exeption.printError();
            list = null;
        }
    }
}
