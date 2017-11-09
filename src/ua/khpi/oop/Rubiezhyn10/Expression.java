package ua.khpi.oop.Rubiezhyn10;

import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

/**
 * Interface, that program use for sort object in container
 *
 * @author Rubezhin Evgeniy
 * Data 09.11.2017
 * */
interface Expression{
    /**
     * isEqual method.
     * Method return the way from console or from static key
     *
     * @param v1 object that will be compare
     * @param v2 second object that will be compare
     * @param side direction of sorting
     * @return boolean - return compare of objects
     * */
    boolean isEqual(PrisonerInfo v1, PrisonerInfo v2, boolean side);
}
