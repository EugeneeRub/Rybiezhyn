package ua.khpi.oop.Rubiezhyn11_12;

/**
 * Class-exception
 *
 * @author Rubezhin Evgeniy
 * Data 23.11.2017
 * */
class NotMatchedDataExeption extends Exception{
    private String text = null;

    NotMatchedDataExeption(String text){
        this.text = text;
    }

    public void printError(){
        System.err.println("Data that must match, doesn`t match in this text =======>  " + text );
    }
}
