package ua.khpi.oop.Rybiezhyn08;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ua.khpi.oop.Rybiezhyn07.Person;
import ua.khpi.oop.Rybiezhyn07.PoliceCardBoard;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that read info from JSON file
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class ReadFromJSON {

    /**
     * Method that read array from JSON file
     * and set it to PrisonerInfo array
     * then set it to PoliceCardBoard object
     *
     * @return policecardboard return ready object from JSON file
     * */
    public PoliceCardBoard readFromFile(String fileWay){
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(fileWay));

            PoliceCardBoard cardBoard = new PoliceCardBoard((int)((long) object.get("number")));
            JSONArray array = (JSONArray) object.get("prisoners");
            PrisonerInfo prisoners[] = new PrisonerInfo[array.size()];
            for (int i = 0; i < prisoners.length; i++) {
                prisoners[i] = new PrisonerInfo();
            }
            int counter = 0;
            Iterator<JSONObject> it = array.iterator();
            while (it.hasNext()){
                JSONObject obj = it.next();
                {
                    Person person = new Person();
                    person.setMPIB((String) obj.get("name"));
                    person.setMDateOfBirthd((String) obj.get("birthday"));
                    person.setMGrowth((float) ((double) obj.get("growth")));
                    person.setMColorEyes((String) obj.get("colorEyes"));
                    prisoners[counter].setPerson(person);
                }
                prisoners[counter].setMDateOfGoToJail((String) obj.get("toJail"));
                prisoners[counter].setMDateOfGoFromJail((String) obj.get("fromJail"));

                JSONArray arr = (JSONArray) obj.get("signs");
                LinkedList<String> list = new LinkedList<>();
                Iterator it2 = arr.iterator();
                while (it2.hasNext()){
                    list.add((String) it2.next());
                }
                prisoners[counter].setMListOfSpecialSigns(list);

                counter++;
            }
            cardBoard.setArrayOfPrisoner(prisoners);
            return cardBoard;
        }catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
