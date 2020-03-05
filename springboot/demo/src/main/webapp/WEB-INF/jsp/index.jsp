<%--
  Created by IntelliJ IDEA.
  User: Rechard
  Date: 2019/8/15
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="user" action="/demo/user/save">
    <form:errors path="*" cssClass="errorBox"/>
<table>
    <tr>
        <td>First Name:</td>
        <td><form:input path="firstName"/></td>
            <%-- Show errors for firstName field --%>
        <td><form:errors path="firstName"/></td>
    </tr>

    <tr>
        <td>Last Name:</td>
        <td><form:input path="lastName"/></td>
            <%-- Show errors for lastName field --%>
        <td><form:errors path="lastName"/></td>
    </tr>

    <tr>
        <td>email:</td>
        <td><form:radiobutton path="hasEmail" value="true"/></td>
    </tr>
    <tr>
        <td colspan="3">
            <input type="submit" value="Save Changes"/>
        </td>
    </tr>
    </table>
</form:form>



</body>
</html>
