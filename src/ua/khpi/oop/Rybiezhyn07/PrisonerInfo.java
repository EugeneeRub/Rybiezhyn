package ua.khpi.oop.Rybiezhyn07;

import javax.swing.text.html.HTMLDocument;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that has info about prisoner
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class PrisonerInfo implements Serializable {
    private Person person;// person info
    private LinkedList<String> mListOfSpecialSigns;// list of signs
    private String mDateOfGoToJail;// date of go to jail
    private String mDateOfGoFromJail;// date of go from jail

    /**
     * Constructor that set null to all types
     * */
    public PrisonerInfo() {
        person = null;
        mListOfSpecialSigns = null;
        mDateOfGoToJail = null;
        mDateOfGoFromJail = null;
    }

    /**
     * Constructor that set data to data
     *
     * @param person person info
     * @param mListOfSpecialSigns list of signs
     * @param mDateOfGoToJail date of go to jail
     * @param mDateOfGoFromJail date of go from jail
     * */
    public PrisonerInfo(Person person, LinkedList<String> mListOfSpecialSigns, String mDateOfGoToJail,
                        String mDateOfGoFromJail) {
        this.person = person;
        this.mListOfSpecialSigns = mListOfSpecialSigns;
        this.mDateOfGoToJail = mDateOfGoToJail;
        this.mDateOfGoFromJail = mDateOfGoFromJail;
    }

    /**
     * Getter method for person
     *
     * @return person info about person
     * */
    synchronized public Person getPerson() {
        return person;
    }

    /**
     * Setter method for person
     *
     * @param person info about person
     * */
    synchronized public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Setter method for mListOfSpecialSigns
     *
     * @return mListOfSpecialSigns list of signs
     * */
    synchronized public LinkedList<String> getMListOfSpecialSigns() {
        return mListOfSpecialSigns;
    }

    /**
     * Setter method for mListOfSpecialSigns
     *
     * @param mListOfSpecialSigns list of signs
     * */
    synchronized public void setMListOfSpecialSigns(LinkedList<String> mListOfSpecialSigns) {
        this.mListOfSpecialSigns = mListOfSpecialSigns;
    }

    /**
     * Getter method for mDateOfGoToJail
     *
     * @return mDateOfGoToJail date of got to jail
     * */
    synchronized public String getMDateOfGoToJail() {
        return mDateOfGoToJail;
    }

    /**
     * Setter method for mDateOfGoToJail
     *
     * @param mDateOfGoToJail date of got to jail
     * */
    synchronized public void setMDateOfGoToJail(String mDateOfGoToJail) {
        this.mDateOfGoToJail = mDateOfGoToJail;
    }

    /**
     * Getter method for mDateOfGoFromJail
     *
     * @return mDateOfGoFromJail date of got from jail
     * */
    synchronized public String getMDateOfGoFromJail() {
        return mDateOfGoFromJail;
    }

    /**
     * Setter method for mDateOfGoFromJail
     *
     * @param mDateOfGoFromJail date of got from jail
     * */
    synchronized public void setMDateOfGoFromJail(String mDateOfGoFromJail) {
        this.mDateOfGoFromJail = mDateOfGoFromJail;
    }
}
