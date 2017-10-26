package ua.khpi.oop.Rybiezhyn07;
/**
 * Class that has info about all prisoners
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class PoliceCardBoard {
    private int numberOfCardBoard;// number of card board
    private PrisonerInfo[] arrayOfPrisoner;// array of prisoners

    /**
     * Constructor that set number of card board
     *
     * @param numberOfCardBoard number that set in numberOfCardBoard
     * */
    public PoliceCardBoard(int numberOfCardBoard){
        this.numberOfCardBoard = numberOfCardBoard;
        arrayOfPrisoner = null;
    }

    /**
     * Getter method for numberOfCardBoard
     *
     * @return numberOfCardBoard count of card board
     * */
    synchronized public int getNumberOfCardBoard(){
        return numberOfCardBoard;
    }

    /**
     * Getter method for array of prisoners
     *
     * @return arrayOfPrisoner array of prisoners
     * */
    synchronized public PrisonerInfo[] getArrayOfPrisoner() {
        return arrayOfPrisoner;
    }

    /**
     * Setter method for array of prisoners
     *
     * @param array array of prisoners
     * */
    synchronized public void setArrayOfPrisoner(PrisonerInfo[] array) {
        arrayOfPrisoner = array;
    }

    /**
     * Method that add new prisoner to array of prisoners
     *
     * @param prisoner object that will be added to array
     * */
    synchronized public void addPrisoner(PrisonerInfo prisoner){
        if (arrayOfPrisoner == null){
            arrayOfPrisoner = new PrisonerInfo[0];
        }
        int c = arrayOfPrisoner.length;
        PrisonerInfo[] copyArrayOfPrisoner = new PrisonerInfo[arrayOfPrisoner.length + 1];
        for (int i = 0; i < c; i++) {
            copyArrayOfPrisoner[i] = arrayOfPrisoner[i];
        }
        copyArrayOfPrisoner[c] = prisoner;
        arrayOfPrisoner = null;
        arrayOfPrisoner = copyArrayOfPrisoner;
    }

    /**
     * Method that remove prisoner from array of prisoners
     *
     * @param index object will be delete by this index
     * @return true/false return boolean type, that say that object was deleted
     * */
    synchronized public boolean removePrisoner(int index){
        if (arrayOfPrisoner == null || arrayOfPrisoner.length == 0 || (index < 0 || index >= arrayOfPrisoner.length)){
            System.out.println("Can not to delete");
           return false;
        }
        int c = arrayOfPrisoner.length;
        PrisonerInfo[] copyArrayOfPrisoner = new PrisonerInfo[arrayOfPrisoner.length - 1];
        for (int i = 0, j = 0; i < c; i++) {
            if (i != index){
                copyArrayOfPrisoner[j] = arrayOfPrisoner[i];
                j++;
            }
        }
        arrayOfPrisoner = null;
        arrayOfPrisoner = copyArrayOfPrisoner;
        return true;
    }

    /**
     * Method that return prisoner by index
     *
     * @param index object will be return by this index
     * @return arrayOfPrisoner[index] return object by index
     * */
    synchronized public PrisonerInfo get(int index){
        if (index < arrayOfPrisoner.length
                && index >= 0
                && arrayOfPrisoner != null){
            return arrayOfPrisoner[index];
        }
        return null;
    }
}
