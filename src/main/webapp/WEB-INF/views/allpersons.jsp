<%-- 
    Document   : allpersons
    Created on : Feb 11, 2017, 7:09:40 PM
    Author     : danielhidekicassi
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Persons</title>
 
    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
 
</head>
 
 
<body>
    <h2>List of Persons</h2>  
    <table>
        <tr>
            <td>NAME</td><td>CPF</td><td>Edit</td><td>Delete</td>
        </tr>
        <c:forEach items="${persons}" var="person">
            <tr>
            <td>${person.name}</td>
            <td>${person.cpf}</td>
            <td><a href="<c:url value='/edit-${person.cpf}-person' />">${person.cpf}</a></td>
            <td><a href="<c:url value='/delete-${person.cpf}-person' />">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="<c:url value='/new' />">Add New Person</a>
</body>
</html>
