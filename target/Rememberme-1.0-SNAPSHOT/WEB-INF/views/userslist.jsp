<%--
  Created by IntelliJ IDEA.
  User: anhtuan
  Date: 22/12/2017
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Home Page: List of Users</title>
</head>
<body>
    <%@include file="authheader.jsp"%>
    <span>List of Users</span>
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>SSO ID</th>
                <th>State</th>
                <sec:authorize access="hasAnyRole('ADMIN', 'DBA')"><th>Edit User</th></sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN')"><th>Delete User</th></sec:authorize>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.ssoId}</td>
                <td>${user.state}</td>
                <sec:authorize access="hasAnyRole('ADMIN', 'DBA')">
                    <td><a href="<c:url value="/edit"/>">Edit</a></td>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <td><a href="<c:url value="/delete"/>">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
