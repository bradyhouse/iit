/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd466;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.sql.DataSource;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author Christopher
 */
@WebService(serviceName = "LSWebService")
public class LSWebService {

    private static final String UNKNOWN = "????";
    private static final String DATABASE_RESPONSE_SUCCESS = "200"; // Success
    // ToDo ~ These variables should be used to create a Model class
    private String //all of the following correspond such that fullName1 
            //has info fullInfo1 and fullName1 and fullInv1 are 1 complete order
            fullName1 = LSWebService.UNKNOWN,
            fullName2 = LSWebService.UNKNOWN,
            fullName3 = LSWebService.UNKNOWN,
            fullName4 = LSWebService.UNKNOWN,
            fullInfo1 = LSWebService.UNKNOWN,
            fullInfo2 = LSWebService.UNKNOWN,
            fullInfo3 = LSWebService.UNKNOWN,
            fullInfo4 = LSWebService.UNKNOWN,
            fullInv1 = LSWebService.UNKNOWN,
            fullInv2 = LSWebService.UNKNOWN,
            fullInv3 = LSWebService.UNKNOWN,
            fullInv4 = LSWebService.UNKNOWN,
            message = LSWebService.UNKNOWN, //message indicating item is unselected
            quantity = LSWebService.UNKNOWN, //quantity of the unslected item
            item = LSWebService.UNKNOWN, //item that is unselected, there is only one
            databaseResponse = LSWebService.DATABASE_RESPONSE_SUCCESS;  // used to store database response messages

    private ArrayList<String> isPending; //array of two items with status "pending"

    @Resource(name = "leatherStore")
    private DataSource leatherStore;
    private Connection connection;

    /**
     * getMessage web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getMessage")
    public String getMessage() {
        if (this.openConnection()) {
            try {
                PreparedStatement p = this.connection.prepareStatement("SELECT * FROM `unselected_items` WHERE 1");
                ResultSet r = p.executeQuery();
                r.next();
                int Inv_ID = r.getInt("Inventory_ID");
                System.out.print("Inventory_ID : " + Inv_ID);
                r.close();
                PreparedStatement p1 = this.connection.prepareStatement("select * from `inventory` where ID = " + Inv_ID);
                ResultSet r1 = p1.executeQuery();
                r1.next();
                this.item = r1.getString("item");
                double quantity = r1.getDouble("quantity");
                r1.close();
                PreparedStatement p2 = this.connection.prepareStatement("select * from `orderless` where 1");
                ResultSet r2 = p2.executeQuery();
                r2.next();
                this.message = r2.getString("withoutorder");
                r2.close();
                System.out.print("###Orderless Items###");
                System.out.print(item + ": " + "Quantity: " + quantity + " Status: " + message);
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.message);
    }

    /**
     * getQuantity web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getQuantity")
    public String getQuantity() {
        if (this.openConnection()) {
            try {
                PreparedStatement ps = this.connection.prepareStatement("select * from `inventory` where ID in ( select orderless_ID from `unselected_items` ) and ID in ( select ID from `orderless` )");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String itm = rs.getString("item");
                    String quantity = rs.getString("quantity");
                    Date date = rs.getDate("lastmodified");
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String stringDate = df.format(date);
                    if (this.quantity == LSWebService.UNKNOWN) {
                        this.quantity = itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    } else {
                        this.quantity += itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.quantity);
    }

    /**
     * getItem web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getItem")
    public String getItem() {
        if (this.openConnection()) {
            try {
                //begin retrieve unselected_items
                PreparedStatement p = this.connection.prepareStatement("select * from `unselected_items` where orderless_ID = ?");
                p.setInt(1, 1);
                ResultSet r = p.executeQuery();
                r.next();
                int Inv_ID = r.getInt("Inventory_ID");
                r.close();
                //#######################################################################################################
                PreparedStatement p1 = this.connection.prepareStatement("select * from `inventory` where ID = " + Inv_ID);
                ResultSet r1 = p1.executeQuery();
                r1.next();
                this.item = r1.getString("item");
                r1.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.item);
    }

    /**
     * getPending web method
     *
     * @return string array list
     */
    @WebMethod(operationName = "getPending")
    public ArrayList<String> getPending() {

        if (this.openConnection()) {
            this.isPending = new ArrayList<String>();
            try {

                //begin retrieve pending orders
                PreparedStatement pend = this.connection.prepareStatement("select * from `inventory` where status = ?");
                pend.setString(1, "pending");
                ResultSet rend = pend.executeQuery();
                while (rend.next()) {
                    this.isPending.add(rend.getString("ITEM"));
                }
                rend.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }

        return this.validate(this.isPending);
    }

    /**
     * getFullName1 web method
     *
     * @return String
     */
    @WebMethod(operationName = "getFullName1")
    public String getFullName1() {
        if (this.openConnection()) {
            try {

                PreparedStatement ps = this.connection.prepareStatement("select * from `customers` where ID = ?");
                ps.setInt(1, 1);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                this.fullName1 = firstName + " " + lastName;
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.closeConnection();
        return this.validate(this.fullName1);

    }

    /**
     * Inquiry method added in response to our demo feedback. This method
     * demonstrates a web method that accepts input parameters.
     *
     * @param id integer equal to the id of the customer
     * @return
     */
    @WebMethod(operationName = "getCustomerDetailHtml")
    public String getCustomerDetailHtml(String criteria) {

        String htmlStartTable = "<table class=\"table\" border=\"1\">",
                htmlEndTable = "</table>",
                htmlStartRow = "<tr>",
                htmlEndRow = "</tr>",
                htmlTableHeader = "<thead><th>Id</th>"
                + "<th>First Name</th>"
                + "<th>Last Name</th>"
                + "<th>Address</th>"
                + "<th>City</th>"
                + "<th>State</th><thead>",
                htmlStartBody = "<tbody>",
                htmlEndBody = "</tbody>",
                htmlStartCell = "<td>",
                htmlEndCell = "</td>",
                htmlResponse = htmlStartTable + htmlStartRow
                + htmlTableHeader + htmlEndRow + htmlStartBody,
                sqlSelectClause = "SELECT c.id, "
                + "c.firstname, "
                + "c.lastname, "
                + "s.custaddress, "
                + "s.custcity, "
                + "s.custstate "
                + "FROM `customers` AS c "
                + "INNER JOIN `shipping_address` AS s "
                + "ON c.id = s.customer_id ",
                sqlStatement = !criteria.equals("A") ? sqlSelectClause
                        + "WHERE c.id = " + criteria : sqlSelectClause;

        if (this.openConnection()) {
            try {
                PreparedStatement ps = this.connection.prepareStatement(sqlStatement);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    htmlResponse += htmlStartRow
                            + htmlStartCell + rs.getString("id") + htmlEndCell
                            + htmlStartCell + rs.getString("firstname") + htmlEndCell
                            + htmlStartCell + rs.getString("lastname") + htmlEndCell
                            + htmlStartCell + rs.getString("custaddress") + htmlEndCell
                            + htmlStartCell + rs.getString("custcity") + htmlEndCell
                            + htmlStartCell + rs.getString("custstate") + htmlEndCell
                            + htmlEndRow;
                }
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        htmlResponse += htmlEndBody + htmlEndTable;
        this.closeConnection();
        return htmlResponse;
    }

    /**
     * getFullName2 web method
     *
     * @return String
     */
    @WebMethod(operationName = "getFullName2")
    public String getFullName2() {
        if (this.openConnection()) {
            try {

                PreparedStatement ps = this.connection.prepareStatement("select * from `customers` where ID = 2");
                ResultSet rs = ps.executeQuery();
                rs.next();
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                this.fullName2 = firstName + " " + lastName;
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }

        return this.validate(this.fullName2);

    }

    /**
     * getFullName web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullName3")
    public String getFullName3() {

        if (this.openConnection()) {
            try {

                PreparedStatement ps = this.connection.prepareStatement("select * from `customers` where ID = 3");

                ResultSet rs = ps.executeQuery();
                rs.next();
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                this.fullName3 = firstName + " " + lastName;
                rs.close();

            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullName3);
    }

    /**
     * getFullName4 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullName4")
    public String getFullName4() {
        if (this.openConnection()) {
            try {

                PreparedStatement ps = this.connection.prepareStatement("select * from `customers` where ID = ?");
                ps.setInt(1, 4);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                this.fullName4 = firstName + " " + lastName;
                rs.close();

            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullName4);
    }

    /**
     * getFullInfo1 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInfo1")
    public String getFullInfo1() {

        if (this.openConnection()) {
            try {

                //begin retrieve customer info
                PreparedStatement prst = this.connection.prepareStatement("select * from `customer_information` where Customer_ID = 1");
                ResultSet rst = prst.executeQuery();
                rst.next();
                int custinfoID1 = rst.getInt("custinfo_ID");
                rst.close();
                //get CustInfo
                PreparedStatement prst1 = this.connection.prepareStatement("select * from `shipping_address` where ID = " + custinfoID1);
                ResultSet rst1 = prst1.executeQuery();
                rst1.next();
                String address = rst1.getString("custAddress");
                String city = rst1.getString("custCity");
                String state = rst1.getString("custState");
                this.fullInfo1 = address + " " + city + " , " + state;
                rst1.close();
                //end retrieve customer info

            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }

        return this.validate(this.fullInfo1);

    }

    /**
     * getFullInfo2 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInfo2")
    public String getFullInfo2() {
        if (this.openConnection()) {
            try {
                //begin retrieve customer info
                PreparedStatement prst = this.connection.prepareStatement("select * from `customer_information` where Customer_ID = 2");
                ResultSet rst = prst.executeQuery();
                rst.next();
                int custinfoID2 = rst.getInt("custinfo_ID");
                rst.close();
                //get CustInfo
                PreparedStatement prst1 = this.connection.prepareStatement("select * from `shipping_address` where ID = " + custinfoID2);
                ResultSet rst1 = prst1.executeQuery();
                rst1.next();
                String address = rst1.getString("custAddress");
                String city = rst1.getString("custCity");
                String state = rst1.getString("custState");
                this.fullInfo2 = address + " " + city + " , " + state;
                rst1.close();
                //end retrieve customer info
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }

        return this.validate(this.fullInfo2);

    }

    /**
     * getFullInfo3 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInfo3")
    public String getFullInfo3() {
        if (this.openConnection()) {
            try {

                //begin retrieve customer info
                PreparedStatement prst = this.connection.prepareStatement("select * from `customer_information` where Customer_ID = 3");
                ResultSet rst = prst.executeQuery();
                rst.next();
                int custinfoID3 = rst.getInt("custinfo_ID");
                rst.close();
                //get CustInfo
                PreparedStatement prst1 = this.connection.prepareStatement("select * from `shipping_address` where ID = " + custinfoID3);
                ResultSet rst1 = prst1.executeQuery();
                rst1.next();
                String address = rst1.getString("custAddress");
                String city = rst1.getString("custCity");
                String state = rst1.getString("custState");
                this.fullInfo3 = address + " " + city + " , " + state;
                rst1.close();
                //end retrieve customer info
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullInfo3);
    }

    /**
     * getFullInfo4 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInfo4")
    public String getFullInfo4() {

        if (this.openConnection()) {
            try {

                //begin retrieve customer info
                PreparedStatement prst = this.connection.prepareStatement("select * from `customer_information` where Customer_ID = 4");

                ResultSet rst = prst.executeQuery();
                rst.next();
                int custinfoID4 = rst.getInt("custinfo_ID");
                rst.close();
                //get CustInfo
                PreparedStatement prst1 = this.connection.prepareStatement("select * from `shipping_address` where ID = " + custinfoID4);
                ResultSet rst1 = prst1.executeQuery();
                rst1.next();
                String address = rst1.getString("custAddress");
                String city = rst1.getString("custCity");
                String state = rst1.getString("custState");
                this.fullInfo4 = address + " " + city + " , " + state;
                rst1.close();
                //end retrieve customer info

            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullInfo4);
    }

    /**
     * getFullInv1 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInv1")
    public String getFullInv1() {
        if (this.openConnection()) {
            try {
                PreparedStatement ps = this.connection.prepareStatement("select * from `inventory` where ID in ( select Items_ID from `orders` where customers_ID = 1 )");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String itm = rs.getString("item");
                    String quantity = rs.getString("quantity");
                    Date date = rs.getDate("lastmodified");
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String stringDate = df.format(date);
                    if (this.fullInv1 == LSWebService.UNKNOWN) {
                        this.fullInv1 = itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    } else {
                        this.fullInv1 += itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullInv1);
    }

    /**
     * getFullInv2 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInv2")
    public String getFullInv2() {
        if (this.openConnection()) {
            try {
                PreparedStatement ps = this.connection.prepareStatement("select * from `inventory` where ID in ( select Items_ID from `orders` where customers_ID = 2 )");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String itm = rs.getString("item");
                    String quantity = rs.getString("quantity");
                    Date date = rs.getDate("lastmodified");
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String stringDate = df.format(date);
                    if (this.fullInv2 == LSWebService.UNKNOWN) {
                        this.fullInv2 = itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    } else {
                        this.fullInv2 += itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullInv2);
    }

    /**
     * getFullInv3 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInv3")
    public String getFullInv3() {
        if (this.openConnection()) {
            try {
                PreparedStatement ps = this.connection.prepareStatement("select * from `inventory` where ID in ( select Items_ID from `orders` where customers_ID = 3 )");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String itm = rs.getString("item");
                    String quantity = rs.getString("quantity");
                    Date date = rs.getDate("lastmodified");
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String stringDate = df.format(date);
                    if (this.fullInv3 == LSWebService.UNKNOWN) {
                        this.fullInv3 = itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    } else {
                        this.fullInv3 += itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullInv3);
    }

    /**
     * getFullInv4 web method
     *
     * @return string value
     */
    @WebMethod(operationName = "getFullInv4")
    public String getFullInv4() {
        if (this.openConnection()) {
            try {
                PreparedStatement ps = this.connection.prepareStatement("select * from `inventory` where ID in ( select Items_ID from `orders` where customers_ID = 4 )");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String itm = rs.getString("item");
                    String quantity = rs.getString("quantity");
                    Date date = rs.getDate("lastmodified");
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String stringDate = df.format(date);
                    if (this.fullInv4 == LSWebService.UNKNOWN) {
                        this.fullInv4 = itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    } else {
                        this.fullInv4 += itm + " with Quantity: " + quantity + " Modified: " + stringDate;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.closeConnection();
        }
        return this.validate(this.fullInv4);
    }

    /**
     * Validation method used to insure the database call (aka given web method)
     * did not fail based on the value of the internal database response
     * variable. On failure, it will swap the database response variable (aka
     * error message) for the input variable. Otherwise, the input variable
     * value is simply returned.
     *
     * @param response string value
     * @return string value or database response message
     */
    private String validate(String response) {
        return this.databaseResponse != LSWebService.DATABASE_RESPONSE_SUCCESS ? this.databaseResponse
                : response;
    }

    /**
     * Overloaded version of the validation method used to handle String array
     * list responses. On failure, the database response message is added to the
     * input array list variable and then returned.
     *
     * @see LSWebService#validate(java.lang.String)
     * @param response string array list
     * @return string array list or modified array list containing the db error
     */
    private ArrayList<String> validate(ArrayList<String> response) {
        if (this.databaseResponse != LSWebService.DATABASE_RESPONSE_SUCCESS) {
            response.add(this.databaseResponse);
        }
        return response;
    }

    /**
     * Value function / "On Switch" for the connection variable. If the
     * connection does not already exist, then this method will create it.
     * Otherwise it does nothing.
     *
     * @return boolean indicating whether connection was created successfully
     */
    private boolean openConnection() {
        //begin retrieve unselected_items
        if (this.connection == null) {
            try {
                this.connection = leatherStore.getConnection();
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    /**
     * "Off" switch for the connection variable. If the connection variable
     * exists, then this method can be used to close and return it to a "null"
     * state.
     */
    private void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                this.databaseResponse = ex.getMessage();
                Logger.getLogger(LSWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
