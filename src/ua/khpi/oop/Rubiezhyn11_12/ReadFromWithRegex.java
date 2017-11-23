package ua.khpi.oop.Rubiezhyn11_12;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ua.khpi.oop.Rubiezhyn05.MyArrayList;
import ua.khpi.oop.Rybiezhyn07.Person;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class reader for prisoners data
 *
 * This class can load data from console or from .json file and check by regEx
 *
 * @author Rubezhin Evgeniy
 * Data 23.11.2017
 * */
class ReadFromWithRegex {
    private static final String DATEPATTERN = "(0[1-9]|[1-2][0-9]|3[0-1])(\\.|\\,)(0[1-9]|1[0-2])(\\.|\\,)([0-9]{4}|[0-9]{2})";
    private static final String COLOREYESPATTERN = "\\D+";
    private static final String NAMEPATTERN = "((\\w+)|(\\w+ \\w+ \\w+)|([а-яА-Я]+|([а-яА-Я]+ [а-яА-Я]+ [а-яА-Я]+)))";
    private static final String LISTPATTERN = "\\D+";

    /**
     * Method load data from console and check all value by regEx
     *
     * @return PrisonerInfo - return prisoners that was created
     * */
    public static PrisonerInfo readFromConsole() throws NotMatchedDataExeption {
        Scanner in = new Scanner(System.in);
        PrisonerInfo prisoner = new PrisonerInfo();
        Person person = new Person();
        LinkedList<String> list = new LinkedList<>();

        System.out.println("Input the information about prisoner");

        //get info from console about person
        {
            System.out.println("Name, surname and middlename (can input the prisoner name)");

            person.setMPIB(getDataFromConsole(in,NAMEPATTERN));

            System.out.print("Date of birth: ");

            person.setMDateOfBirthd(getDataFromConsole(in,DATEPATTERN));

            System.out.print("Growth: ");
            person.setMGrowth(in.nextFloat());

            System.out.print("Color of eyes: ");
            person.setMColorEyes(getDataFromConsole(in,COLOREYESPATTERN));
        }
        prisoner.setPerson(person);

        System.out.print("When go to jail: ");
        prisoner.setMDateOfGoToJail(getDataFromConsole(in,DATEPATTERN));

        System.out.print("When go from jail: ");
        prisoner.setMDateOfGoFromJail(getDataFromConsole(in,DATEPATTERN));

        System.out.println("Special signs(\"-stop\" for stop adding):");
        Scanner in2 = new Scanner(System.in);
        boolean flag = true;
        do {
            String text = getDataFromConsole(in,LISTPATTERN);
            if (text.equals("-stop")) {
                flag = false;
            } else
                list.add(text);
        } while (flag);
        prisoner.setMListOfSpecialSigns(list);

        return prisoner;
    }

    /**
     * Method get data from console and check it
     * if data are not valid, start repeating the process
     *
     * @param PATTERN - regEx
     * @param in scanner for getting data
     * @return String - return value from console
     * */
    private static String getDataFromConsole(Scanner in,String PATTERN){
        boolean flag = true;
        String data;
        do {
            data = in.nextLine();
            try{
                checkExpression(data,PATTERN);
                flag = false;
            }catch (NotMatchedDataExeption ex){
                ex.printError();
                flag = true;
                System.out.println("Repeat input the data");
            }
        }while (flag);
        return data;
    }

    /**
     * Method get data from file and check it
     * if data are not valid, stop the process
     *
     * @param fileWay way to file
     * @return MyArrayList - return list of prisoners
     * */
    public static MyArrayList<PrisonerInfo> readFromFile(File fileWay) throws NotMatchedDataExeption{
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(fileWay));

            MyArrayList<PrisonerInfo> listOfPrisoners = new MyArrayList<>();
            JSONArray array = (JSONArray) object.get("prisoners");
            PrisonerInfo prisoner = null;
            //int counter = 0;
            Iterator<JSONObject> it = array.iterator();
            String data;
            while (it.hasNext()){
                prisoner = new PrisonerInfo();
                JSONObject obj = it.next();
                {
                    Person person = new Person();
                    data = (String) obj.get("name");
                    checkExpression(data,NAMEPATTERN);
                    person.setMPIB(data);

                    data = (String) obj.get("birthday");
                    checkExpression(data,DATEPATTERN);
                    person.setMDateOfBirthd(data);

                    person.setMGrowth((float) ((double) obj.get("growth")));

                    data = (String) obj.get("colorEyes");
                    checkExpression(data,COLOREYESPATTERN);
                    person.setMColorEyes(data);

                    prisoner.setPerson(person);
                }
                data = (String) obj.get("toJail");
                checkExpression(data,DATEPATTERN);
                prisoner.setMDateOfGoToJail(data);

                data = (String) obj.get("fromJail");
                checkExpression(data,DATEPATTERN);
                prisoner.setMDateOfGoFromJail(data);

                JSONArray arr = (JSONArray) obj.get("signs");
                LinkedList<String> listOfDecarations = new LinkedList<>();
                Iterator it2 = arr.iterator();
                while (it2.hasNext()){
                    data = (String) it2.next();
                    checkExpression(data,LISTPATTERN);
                    listOfDecarations.add(data);
                }
                prisoner.setMListOfSpecialSigns(listOfDecarations);
                listOfPrisoners.add(prisoner);
                //counter++;
            }
            return listOfPrisoners;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method check expression and if we have error throw exception
     *
     * @param data some text
     * @param regex regEx
     * */
    private static void checkExpression(String data,String regex) throws NotMatchedDataExeption {
        if (!checkByRegex(data, regex))
            throw new NotMatchedDataExeption(data);
    }

    /**
     * Method get data from file and check it
     * if data are not valid, stop the process
     *
     * @param data some text
     * @param regEx regEx
     * @return boolean - return valid value of checking regEx
     * */
    private static boolean checkByRegex(String data,String regEx){
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
