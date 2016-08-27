/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 * MP1 Requirement: "Provide an interface called TabletRelatable 
 * with the following methods:
 * • String FindLargestDisplayTablet(Tablet [] tablets );
 * • String FindLowestCostTablet(Tablet [] tablets);
 * • String FindNumberOfCreatedTabletTypes(Tablet []  tablets);
 * • String FindLightestWeightTabletType(Tablet [] tablets);
 * Implement this interface in the Tablet class. Why?" 
 * 
 * Why? Because it allows the methods to be implemented in the
 * base (or super class), making the methods available to all
 * sub classes.
 *
 * @author brady houseknecht
 */
public interface TabletRelatable {

    /**
    * Method that can be used to find the tablet or tablets
    * having the largest display size in an array of tablets.
    * 
    * @param tablets Array of Tablet objects
    * @return string value identifying the tablet or tablets having
    * the largest display size.
    */
    public String findLargestDisplayTablet(Tablets.Tablet[] tablets);

    /**
    * Method that can be used to find the tablet or tablets
    * having the lowest cost in an array of tablets.
    * 
    * @param tablets Array of Tablet objects
    * @return string value identifying the tablet or tablets having
    * the lowest cost.
    */
    public String findLowestCostTablet(Tablets.Tablet[] tablets);

    /**
    * Method that can be used to find the count
    * of each tablet type in a given array of tablets.
    * 
    * @param tablets Array of Tablet objects
    * @return string value identifying the number of tablets of each
    * type.
    */
    public String findNumberOfCreatedTabletTypes(Tablets.Tablet[] tablets);

    /**
    * Method that can be used to find the lightest tablet or tablets
    * in an array of tablets.
    * 
    * @param tablets Array of Tablet objects
    * @return string value identifying the lightest tablet or tablets.
    */
    public String findLightestWeightTabletType(Tablets.Tablet[] tablets);

} // end TabletRelatable
