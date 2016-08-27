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
        <title>City Insert</title>
    </head>
    <body>
        <c:import url="../static/page-header.jsp"/>
        <c:import url="../static/violations.jsp"/>
        <c:choose>
            <c:when test="${not empty city.getId()}">
                <form action="<c:url value="/city/update"/>" method="post">
                    <input type="hidden" id="postback" name="postback" value="${city.getId()} " />
            </c:when>
            <c:otherwise>
                <form action="<c:url value="/city/insert"/>" method="post">
            </c:otherwise>
        </c:choose>
            <fieldset>
                <c:choose>
                    <c:when test="${not empty city.getId()}">
                        <h2>Update City Form</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>Insert City Form</h2>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty city.getId()}">
                        <div>
                           <label for="Id">Id:</label>
                           <input disabled="true" type="text" id="Id" name="Id" value="${city.getId()}" ${not empty city.getId() ? 'readonly="readonly"' : ''}>
                       </div>
                    </c:when>
                </c:choose>
                <div>
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="${city.getName()}">
                </div>
                <div>
                    <label for="countryCode">Country Code:</label>
                    <c:choose>
                        <c:when test="${not empty city.getId()}">
                            <select id="countryCode" name="countryCode" size="1" value="${city.getCountryCode()}">
                                <c:forEach items="${requestScope.countryCodes}" var="code">
                                    <c:choose>
                                        <c:when test="${code==city.getCountryCode()}">
                                            <option selected="true" value="${code}">${code}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${code}">${code}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                           <select id="countryCode" name="countryCode" size="1" >
                               <option value="">Select a Code</option>
                               <c:forEach items="${requestScope.countryCodes}" var="code">
                                    <option value="${code}">${code}</option>
                               </c:forEach>
                           </select>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <label for="district">District:</label>
                    <input type="text" id="district" name="district" value="${city.getDistrict()}">
                </div>
                <div>
                    <label for="population">Population:</label>
                    <input type="text" id="population" name="population" value="${city.getPopulation()}">
                </div>
                <input type="submit" value="submit"/>
                <INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;">
            </fieldset>
        </form>
    </body>
</html>
