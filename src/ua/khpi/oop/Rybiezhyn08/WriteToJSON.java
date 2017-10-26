package ua.khpi.oop.Rybiezhyn08;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ua.khpi.oop.Rybiezhyn07.PoliceCardBoard;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that write info to JSON file
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class WriteToJSON {

    /**
     * Method that write array to JSON file
     * */
    public void writeToFile(PoliceCardBoard cardBoard, String fileWay){
        JSONObject object = new JSONObject();
        object.put("number",cardBoard.getNumberOfCardBoard());
        PrisonerInfo info[] = cardBoard.getArrayOfPrisoner();
        JSONArray array = new JSONArray();
        for (int i = 0; i < info.length; i++) {
            JSONObject obj = new JSONObject();
            {
                obj.put("name", info[i].getPerson().getMPIB());
                obj.put("birthday", info[i].getPerson().getMDateOfBirthd());
                obj.put("growth", info[i].getPerson().getMGrowth());
                obj.put("colorEyes", info[i].getPerson().getMColorEyes());
            }
            obj.put("toJail",info[i].getMDateOfGoToJail());
            obj.put("fromJail",info[i].getMDateOfGoFromJail());

            JSONArray arr = new JSONArray();
            for(String st : info[i].getMListOfSpecialSigns()){
                arr.add(st);
            }
            obj.put("signs",arr);

            array.add(obj);
        }
        object.put("prisoners",array);

        try(FileWriter writer = new FileWriter(fileWay)){
            writer.write(object.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
