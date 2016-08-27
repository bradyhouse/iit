<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="ITM 466 | Assigment 3 | Group 7">
        <meta name="author" content="brady house, christopher hernandez ">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="resources/html5shiv.js"></script>
        <script src="resources/respond.min.js"></script>
        <![endif]-->
        <title>AS3-GROUP7</title>
        <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
        <link href="resources/custom.css" rel="stylesheet">
    </head>
    <body>
        <div class="container enter-stage-south">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h1 id="navbar">Leather Store</h1>
                        </div>
                        <div class="bs-component">
                            <h2> Customer Info </h2>
                            <% 
                                java.lang.String criteria = "A";
                                
                                if (request.getParameterMap().containsKey("customerId")) { 
                                    criteria = request.getParameter("customerId");
                                }
                            %>
                            <form action="index.jsp" class="form-horizontal">
                                <fieldset>
                                    <div class="form-group">
                                        <label for="customerId" class="col-lg-2 control-label">Customer Id:</label>
                                        <div class="col-lg-10">
                                            <select name="customerId" id="customerId" class="form-control">
                                                <option value="1" <% out.write((criteria.equals("1") ? "selected ": "")); %>>1</option>
                                                <option value="2" <% out.write((criteria.equals("2") ? "selected ": "")); %>>2</option>
                                                <option value="3" <% out.write((criteria.equals("3") ? "selected ": "")); %>>3</option>
                                                <option value="4" <% out.write((criteria.equals("4") ? "selected ": "")); %>>4</option>
                                                <option value="A" <% out.write((criteria.equals("A") ? "selected ": "")); %>>All</option>
                                            </select>
                                        </div>
                                    </div>
                                     <div class="form-group">
                                          <div class="col-lg-10 col-lg-offset-2">
                                            <input type="submit" class="btn btn-success" name="submit" value="Search">
                                          </div>
                                     </div>
                                </fieldset>
                            </form>
                            <hr />
                            <%
                            try {
                                edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                 // TODO initialize WS operation arguments here
                                java.lang.String arg0 = criteria;
                                // TODO process result here
                                java.lang.String result = port.getCustomerDetailHtml(arg0);
                                out.println(result);
                            } catch (Exception ex) {
                                // TODO handle custom exceptions here
                            }
                            %>
                            <%-- end web service invocation --%><hr />
                            <br />
                            <br />
                                
                            
                            

                            <h2>Customer Shipping Info</h2>
                            <table class="table" border="1">
                                <thead>
                                    <tr>
                                        <th>Customer Id</th>
                                        <th>Address</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {

                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInfo1();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInfo2();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");

                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInfo3();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>    
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInfo4();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>   
                            <h2>Inventory Info</h2>
                            <table class="table" border="1">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Info</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInv1();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInv2();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInv3();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullInv4();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <h2>Customers</h2>
                            <table class="table" border="1">
                                <thead>
                                    <tr>
                                        <th>Customer Id</th>
                                        <th>Full Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullName1();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullName2();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullName3();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>
                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getFullName4();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <h2>UnSelected Items</h2>
                            <table class="table" border="1">
                                <thead>
                                    <tr>
                                        <th>Item Id</th>
                                        <th>Item</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>

                                            <%-- start web service invocation --%>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getItem();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                        <td>
                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getMessage();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %> 
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <br/>

                            <h2>Orders</h2>
                            <table class="table" border="1">
                                <thead>
                                    <tr>
                                        <th>Customer Id</th>
                                        <th>Full Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>

                                            <%
                                                try {
                                                    edu.iit.sat.itmd466.LSWebService_Service service = new edu.iit.sat.itmd466.LSWebService_Service();
                                                    edu.iit.sat.itmd466.LSWebService port = service.getLSWebServicePort();
                                                    // TODO process result here
                                                    java.lang.String result = port.getQuantity();
                                                    out.println(result);
                                                } catch (Exception ex) {
                                                    out.println("<h6>" + ex.getMessage() + "</h6>");
                                                }
                                            %>
                                            <%-- end web service invocation --%>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <br/>
                <div class="modal-footer text-danger">
                    <div class="text-left text-danger">
                        <a href="https://github.com/bradyhouse/ITM466/tree/master/assignment3" title="Access Github Repository" target="_blank">@github</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        <a href="http://localhost:8080/LeatherStoreWebService/readme.markdown" title="View project readme file" target="_blank">readme</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        <a href="http://localhost:8080/LeatherStoreWebServiceClient/resources/apidocs/index.html" title="View service api doc" target="_blank">apidocs</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        <a href="http://localhost:8080/LeatherStoreWebService/LSWebService?Tester" title="Open web service tester page" target="_blank">service tester</a>
                    </div>
                    @ 04-11-15
                </div>
            </div>
        </div>
    </body>
</html>
