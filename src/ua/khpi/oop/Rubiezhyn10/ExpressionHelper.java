package ua.khpi.oop.Rubiezhyn10;

import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

/**
 * Class helper for compare two objects
 *
 * @author Rubezhin Evgeniy
 * Data 09.11.2017
 * */
class ExpressionHelper{
    /**
     * useMSNEquals method.
     * Method return compare of two objects
     *
     * @param prisoner1 object that will be compare
     * @param prisoner2 second object that will be compare
     * @param side direction of sorting
     * @return boolean - return compare of objects
     * */
    static boolean useMSNEquals(PrisonerInfo prisoner1, PrisonerInfo prisoner2, boolean side){
        int v1 = createNumberFromString(prisoner1.getPerson().getMPIB());
        int v2 = createNumberFromString(prisoner2.getPerson().getMPIB());
        if (side)
            return v1 < v2;
        else
            return v1 > v2;
    }

    /**
     * useBirthdayEquals method.
     * Method return compare of two objects
     *
     * @param prisoner1 object that will be compare
     * @param prisoner2 second object that will be compare
     * @param side direction of sorting
     * @return boolean - return compare of objects
     * */
    static boolean useBirthdayEquals(PrisonerInfo prisoner1,PrisonerInfo prisoner2,boolean side){
        int v1 = createNumberFromString(prisoner1.getPerson().getMDateOfBirthd());
        int v2 = createNumberFromString(prisoner2.getPerson().getMDateOfBirthd());
        if (side)
            return v1 < v2;
        else
            return v1 > v2;
    }

    /**
     * useGoToJailEquals method.
     * Method return compare of two objects
     *
     * @param prisoner1 object that will be compare
     * @param prisoner2 second object that will be compare
     * @param side direction of sorting
     * @return boolean - return compare of objects
     * */
    static boolean useGoToJailEquals(PrisonerInfo prisoner1,PrisonerInfo prisoner2,boolean side){
        int v1 = createNumberFromString(prisoner1.getMDateOfGoToJail());
        int v2 = createNumberFromString(prisoner2.getMDateOfGoToJail());
        if (side)
            return v1 < v2;
        else
            return v1 > v2;
    }

    /**
     * useGoFromJailEquals method.
     * Method return compare of two objects
     *
     * @param prisoner1 object that will be compare
     * @param prisoner2 second object that will be compare
     * @param side direction of sorting
     * @return boolean - return compare of objects
     * */
    static boolean useGoFromJailEquals(PrisonerInfo prisoner1,PrisonerInfo prisoner2,boolean side){
        int v1 = createNumberFromString(prisoner1.getMDateOfGoFromJail());
        int v2 = createNumberFromString(prisoner2.getMDateOfGoFromJail());
        if (side)
            return v1 < v2;
        else
            return v1 > v2;
    }

    /**
     * useGoFromJailEquals method.
     * Method return compare of two objects
     *
     * @param str string that will be parse on char
     * @return int - sum of charset number
     * */
    static int createNumberFromString(String str){
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i);
        }
        return sum;
    }
}
