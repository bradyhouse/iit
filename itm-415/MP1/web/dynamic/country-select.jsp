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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>world.Country List</title>
    </head>
    <body>
        <c:import url="../static/page-header.jsp"/>
        <c:import url="../static/violations.jsp"/>
        <c:import url="../static/successes.jsp"/>
        <h1>world.Country List:</h1>
        <table style="font-size:small;" border="1">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Continent</th>
                    <th>Region</th>
                    <th>SurfaceArea</th>
                    <th>IndepYear</th>
                    <th>Population</th>
                    <th>LifeExpectancy</th>
                    <th>GNP</th>
                    <th>GNPOld</th>
                    <th>LocalName</th>
                    <th>GovernmentForm</th>
                    <th>HeadOfState</th>
                    <th>Capital</th>
                    <th>Code2</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.countries}" var="country">
                    <tr>
                        <c:url value="/country/update" var="updateURL">
                            <c:param name="code" value="${country.getCode()}"/>
                        </c:url>
                        <c:url value="/country/delete" var="deleteURL">
                            <c:param name="code" value="${country.getCode()}"/>
                        </c:url>
                        <td>${country.getCode()}</td>
                        <td>${country.getName()}</td>
                        <td>${country.getContinent()}</td>
                        <td>${country.getRegion()}</td>
                        <td>${country.getSurfaceArea()}</td>
                        <td>${country.getIndepYear()}</td>
                        <td>${country.getPopulation()}</td>
                        <td>${country.getLifeExpectancy()}</td>
                        <td>${country.getGNP()}</td>
                        <td>${country.getGNPOld()}</td>
                        <td>${country.getLocalName()}</td>
                        <td>${country.getGovernmentForm()}</td>
                        <td>${country.getHeadOfState()}</td>
                        <td>${country.getCapital()}</td>
                        <td>${country.getCode2()}</td>
                        <td><a href="${deleteURL}">Delete</a></td>
                        <td><a href="${updateURL}">Update</a></td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
</html>
