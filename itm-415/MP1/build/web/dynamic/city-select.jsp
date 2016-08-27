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
        <title>world.City List:</title>
    </head>
    <body>
        <c:import url="../static/page-header.jsp"/>
        <c:import url="../static/violations.jsp"/>
        <c:import url="../static/successes.jsp"/>
        <h1>world.City List:</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Country Code</th>
                    <th>District</th>
                    <th>Population</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.cities}" var="city">
                    <tr>
                        <c:url value="/city/update" var="updateURL">
                            <c:param name="id" value="${city.getId()}"/>
                        </c:url>
                        <c:url value="/city/delete" var="deleteURL">
                            <c:param name="id" value="${city.getId()}"/>
                        </c:url>
                        <td>${city.getId()}</td>
                        <td>${city.getName()}</td>
                        <td>${city.getCountryCode()}</td>
                        <td>${city.getDistrict()}</td>
                        <td>${city.getPopulation()}</td>
                        <td><a href="${deleteURL}">Delete</a></td>
                        <td><a href="${updateURL}">Update</a></td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
</html>
