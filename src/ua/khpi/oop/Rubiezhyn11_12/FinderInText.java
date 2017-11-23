package ua.khpi.oop.Rubiezhyn11_12;

import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class finder
 *
 * This class can search data by special regEx that input the user of this program
 *
 * Не забыть усовершенствовать!!!!!!
 *
 * @author Rubezhin Evgeniy
 * Data 23.11.2017
 * */
public class FinderInText {

    /**
     * Method start special method by language
     *
     * @param prisoner data
     * @param tempRus regEx for russian text
     * @param tempEng regEx for english text
     * @return boolean - valid value
     * */
    public boolean finder(PrisonerInfo prisoner, String tempRus, String tempEng){
        LinkedList<String> list = prisoner.getMListOfSpecialSigns();
        char ch = list.get(0).charAt(0);
        if (( ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122))
           return specialFinder(list,tempEng,parseRegex(tempEng));
        else if (( ch >= 'а' && ch <= 'я' || ch >= 'А' && ch <= 'Я'))
           return specialFinder(list,tempRus,parseRegex(tempRus));
        return false;
    }

    /**
     * Method that search data by regEx
     *
     * @param list list of texts
     * @param PATTERN regEx for russian text
     * @param specialKeys keys that was parsed in parsing method
     * @return boolean - valid value
     * */
    private boolean specialFinder(LinkedList<String> list, String PATTERN, LinkedList<LinkedList<String>>  specialKeys) {
        Pattern pattern = Pattern.compile(PATTERN);
        for (String data : list){
            Matcher matcher = pattern.matcher(data);
            if (matcher.find())
                return true;
        }
        return deepSearching(list,specialKeys);
    }

    /**
     * Method that use special searching to find information that was in regEx
     *
     * @param list list of texts
     * @param specialKeys keys that was parsed in parsing method
     * @return boolean - valid value
     * */
    private boolean deepSearching(LinkedList<String> list, LinkedList<LinkedList<String>> specialKeys) {
        int numberOfWord[] = new int[specialKeys.size()];
        int sum = 0;
        for (String text : list) {
            String arraOfWords[] = text.split(" ");

            for (int n : numberOfWord)
                sum += n;
            if (sum == specialKeys.size()) {
                return true;
            } else {
                numberOfWord = new int[specialKeys.size()];
            }
            for (String str : arraOfWords) {
                StringBuilder word = new StringBuilder(str);
                char ch = word.charAt(word.length()-1);
                if (ch == '.' || ch == ',' || ch == '!' || ch == '?') {
                    word.delete(word.length()-1,word.length());
                }
                goThrow:
                for (int i = 0; i < specialKeys.size(); i++) {
                    if (numberOfWord[i] != 0)
                        continue;
                    LinkedList<String> list1 = specialKeys.get(i);
                    for (String word2 : list1) {
                        if (word.toString().equals(word2)) {
                            numberOfWord[i]++;
                            break goThrow;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method that parse regular expression from text
     *
     * @param regex regEx
     * @return LinkedList - list of keys from regex
     * */
    private LinkedList<LinkedList<String>> parseRegex(String regex){
        LinkedList<LinkedList<String>> list = new LinkedList<>();
        LinkedList<String> listOfString = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < regex.length(); i++) {
            char ch = regex.charAt(i);
            if (ch == '|'){
                listOfString.add(builder.toString());
                builder.delete(0,builder.length());
            }else if (( ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122)
                    || ( ch >= 'а' && ch <= 'я' || ch >= 'А' && ch <= 'Я')) {
                builder.append((char) ch);
            } else {
                if (!builder.toString().isEmpty()) {
                    listOfString.add(builder.toString());
                    list.add(listOfString);
                    listOfString = new LinkedList<>();
                }
                builder.delete(0,builder.length());
            }
        }
        return list;
    }
}
