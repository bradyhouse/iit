<%-- 
Copyright 2013 Brady Houseknecht

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>world.CountryLanguage Insert/Update</title>
    </head>
    <body>
        <c:import url="../static/page-header.jsp"/>
        <c:import url="../static/violations.jsp"/>
        <c:choose>
            <c:when test="${not empty language}">
                <form action="<c:url value="/language/update"/>" method="post">
                    <input type="hidden" id="postbackCountryCode" name="postbackCountryCode" value="${language.getCountryCode()} " />
                    <input type="hidden" id="postbackLanguage" name="postbackLanguage" value="${language.getLanguage()} " />
            </c:when>
            <c:otherwise>
                <form action="<c:url value="/language/insert"/>" method="post">
            </c:otherwise>
        </c:choose>
            <fieldset>
                <c:choose>
                    <c:when test="${not empty language.getCountryCode() && not empty language.getLanguage() }">
                        <h2>Update Country Language</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>Insert Country Language</h2>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty language.getCountryCode() && not empty language.getLanguage() }">
                        <div>
                           <label for="countryCode">Country Code:</label>
                           <input disabled="true" type="text" id="countryCode" name="countryCode" value="${language.getCountryCode()}" ${not empty city.getCountryCode() ? 'readonly="readonly"' : ''}>
                       </div>
                        <div>
                            <label for="language">Language:</label>
                            <input type="text" disabled="true" id="language" name="language" value="${language.getLanguage()}">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <label for="countryCode">Country Code:</label>
                            <select id="countryCode" name="countryCode" size="1" >
                                  <option value="">Select a Code</option>
                                  <c:forEach items="${requestScope.countryCodes}" var="code">
                                       <option value="${code}">${code}</option>
                                  </c:forEach>
                              </select>
                        </div>
                         <div>
                            <label for="language">Language:</label>
                            <input type="text" id="language" name="language" value="${language.getLanguage()}">
                        </div>
                    </c:otherwise>
                </c:choose>
                
                <div>
                    <label for="isofficial">Is Official:</label>
                    <c:choose>
                        <c:when test="${not empty language.getCountryCode() && not empty language.getLanguage()}">
                            <select id="isofficial" name="isofficial" size="1" >
                                <c:choose>
                                    <c:when test="${language.getIsOfficial()}">
                                       <option selected value="1">T</option>
                                       <option value="0">F</option>
                                    </c:when>
                                    <c:otherwise>
                                       <option selected value="0">F</option>
                                       <option value="1">T</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>        
                        </c:when>
                        <c:otherwise>
                             <select id="isofficial" name="isofficial" size="1" >
                                <option selected value="0">F</option>
                                <option value="1">T</option>
                             </select>  
                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <label for="percentage">Percentage:</label>
                    <input type="text" id="percentage" name="percentage" value="${language.getPercentage()}">
                </div>
                <input type="submit" value="submit"/>
                <INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;">
            </fieldset>
        </form>
    </body>
</html>
