package ua.khpi.oop.Rybiezhyn07;

import java.awt.*;
import java.io.Serializable;


/**
 * Class that has info about person
 *
 * @author Rubezhin Evgeniy
 * Data 25.10.2017
 * */
public class Person implements Serializable {
    private String mPIB;// name, surname or nickname
    private String mDateOfBirthd;// date of birth
    private float mGrowth;// growth of person
    private String mColorEyes;// eyes color

    /**
     * Constructor that set null to all types
     * */
    public Person(){
        mPIB = null;
        mDateOfBirthd = null;
        mGrowth = 0;
        mColorEyes = null;
    }

    /**
     * Constructor that set data to data
     *
     * @param mPIB name, surname or can be a nickname
     * @param mDateOfBirthd date of birth
     * @param mGrowth growth of person
     * @param mColorEyes eyes color
     * */
    public Person(String mPIB, String mDateOfBirthd, float mGrowth, String mColorEyes) {
        this.mPIB = mPIB;
        this.mDateOfBirthd = mDateOfBirthd;
        this.mGrowth = mGrowth;
        this.mColorEyes = mColorEyes;
    }

    /**
     * Getter method for mPIB
     *
     * @return mPIB name and surname
     * */
     public String getMPIB() {
        return mPIB;
    }

    /**
     * Setter method for mPIB
     *
     * @param mPIB name and surname or nickname
     * */
     public void setMPIB(String mPIB) {
        this.mPIB = mPIB;
    }

    /**
     * Getter method for mDateOfBirthd
     *
     * @return mDateOfBirthd date of birth
     * */
     public String getMDateOfBirthd() {
        return mDateOfBirthd;
    }

    /**
     * Setter method for mDateOfBirthd
     *
     * @param mDateOfBirthd date of birth
     * */
     public void setMDateOfBirthd(String mDateOfBirthd) {
        this.mDateOfBirthd = mDateOfBirthd;
    }

    /**
     * Getter method for mGrowth
     *
     * @return mGrowth growth of person
     * */
     public float getMGrowth() {
        return mGrowth;
    }

    /**
     * Setter method for mGrowth
     *
     * @param mGrowth growth of person
     * */
     public void setMGrowth(float mGrowth) {
        this.mGrowth = mGrowth;
    }

    /**
     * Getter method for mColorEyes
     *
     * @return mColorEyes color eyes
     * */
     public String getMColorEyes() {
        return mColorEyes;
    }

    /**
     * Setter method for mColorEyes
     *
     * @param mColorEyes color eyes
     * */
     public void setMColorEyes(String mColorEyes) {
        this.mColorEyes = mColorEyes;
    }
}
