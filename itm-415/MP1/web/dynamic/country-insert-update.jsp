<%-- 
Copyright 2013 Brady Houseknecht

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific country governing permissions and
limitations under the License.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>world.Country Insert/Update</title>
    </head>
    <body>
        <c:import url="../static/page-header.jsp"/>
        <c:import url="../static/violations.jsp"/>
        <c:choose>
            <c:when test="${not empty country.getCode()}">
                <form action="<c:url value="/country/update"/>" method="post">
                    <input type="hidden" id="postbackCode" name="postbackCode" value="${country.getCode()} " />
            </c:when>
            <c:otherwise>
                <form action="<c:url value="/country/insert"/>" method="post">
            </c:otherwise>
        </c:choose>
            <fieldset>
                <c:choose>
                    <c:when test="${not empty country.getCode()}">
                        <h2>Update World.Country:</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>Insert World.Country:</h2>
                    </c:otherwise>
                </c:choose>
                <table>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty country.getCode()}">
                                <tr>
                                    <td><label for="code">Code:</label></td>
                                    <td><input disabled="true" type="text" id="code" name="code" value="${country.getCode()}" ${not empty country.getCode() ? 'readonly="readonly"' : ''}></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td><label for="code">Code:</label></td>
                                   <td><input type="text" id="code" name="code"></td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        <tr><td><label for="name">Name:</label></td><td><input type="text" id="name" name="name" value="${country.getName()}"></td></tr>
                        <tr><td><label for="continent">Continent:</label></td><td><input type="text" id="continent" name="continent" value="${country.getContinent()}"></td></tr>
                        <tr><td><label for="region">Region:</label></td><td><input type="text" id="region" name="region" value="${country.getRegion()}"></td></tr>
                        <tr><td><label for="surfacearea">SurfaceArea:</label></td><td><input type="text" id="surfacearea" name="surfacearea" value="${country.getSurfaceArea()}"></td></tr>
                        <tr><td><label for="indepyear">IndepYear:</label></td><td><input type="text" id="indepyear" name="indepyear" value="${country.getIndepYear()}"></td></tr>
                        <tr><td><label for="population">Population:</label></td><td><input type="text" id="population" name="population" value="${country.getPopulation()}"></td></tr>
                        <tr><td><label for="lifeexpectancy">LifeExpectancy:</label></td><td><input type="text" id="lifeexpectancy" name="lifeexpectancy" value="${country.getLifeExpectancy()}"></td></tr>
                        <tr><td><label for="gnp">GNP:</label></td><td><input type="text" id="gnp" name="gnp" value="${country.getGNP()}"></td></tr>
                        <tr><td><label for="gnpold">GNPOld:</label></td><td><input type="text" id="gnpold" name="gnpold" value="${country.getGNPOld()}"></td></tr>
                        <tr><td><label for="localname">LocalName:</label></td><td><input type="text" id="localname" name="localname" value="${country.getLocalName()}"></td></tr>
                        <tr><td><label for="governmentform">GovernmentForm:</label></td><td><input type="text" id="governmentform" name="governmentform" value="${country.getGovernmentForm()}"></td></tr>
                        <tr><td><label for="headofstate">HeadOfState:</label></td><td><input type="text" id="headofstate" name="headofstate" value="${country.getHeadOfState()}"></td></tr>
                        <tr><td><label for="capital">Capital:</label></td>
                            <td>
                                <c:choose>
                                  <c:when test="${not empty country.getCode()}">
                                      <select id="capital" name="capital" size="1" value="${country.getCapital()}">
                                          <c:forEach items="${requestScope.cities}" var="city">
                                              <c:choose>
                                                  <c:when test="${city.getId()==country.getCapital()}">
                                                      <option selected="true" value="${city.getId()}">${city.getName()}</option>
                                                  </c:when>
                                                  <c:otherwise>
                                                      <option value="${city.getId()}">${city.getName()}</option>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:forEach>
                                      </select>
                                  </c:when>
                                  <c:otherwise>
                                     <select id="capital" name="capital" size="1" >
                                         <option value="">Select a City</option>
                                         <c:forEach items="${requestScope.cities}" var="city">
                                              <option value="${city.getId()}">${city.getName()}</option>
                                         </c:forEach>
                                     </select>
                                  </c:otherwise>
                              </c:choose>      
                            </td>
                        </tr>
                        <tr><td><label for="code2">Code2:</label></td><td><input type="text" id="code2" name="code2" value="${country.getCode2()}"></td></tr>
                    </tbody>
                </table>
                <input type="submit" value="submit"/>
                <INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;">
            </fieldset>
        </form>
    </body>
</html>
