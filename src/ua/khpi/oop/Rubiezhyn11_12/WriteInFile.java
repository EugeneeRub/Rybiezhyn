package ua.khpi.oop.Rubiezhyn11_12;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Class writer
 *
 * This class load data to file
 *
 * @author Rubezhin Evgeniy
 * Data 23.11.2017
 * */
@SuppressWarnings("all")
public class WriteInFile {

    /**
     * Method load data to .json file
     *
     * @param list list of prisoners
     * @param fileWay way to file
     * */
    public static void writeToFile(Collection<PrisonerInfo> list, String fileWay){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (PrisonerInfo info : list) {
            JSONObject obj = new JSONObject();
            {
                obj.put("name", info.getPerson().getMPIB());
                obj.put("birthday", info.getPerson().getMDateOfBirthd());
                obj.put("growth", info.getPerson().getMGrowth());
                obj.put("colorEyes", info.getPerson().getMColorEyes());
            }
            obj.put("toJail",info.getMDateOfGoToJail());
            obj.put("fromJail",info.getMDateOfGoFromJail());

            JSONArray arr = new JSONArray();
            for(String st : info.getMListOfSpecialSigns()){
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
