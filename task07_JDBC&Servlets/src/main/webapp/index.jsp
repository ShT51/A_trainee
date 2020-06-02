<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
</head>
<body>
<h2>Employees List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<p><a href='<c:url value="/employee.jsp" />'>Get Employee by Email</a></p>
<table>
    <tr><th>FirstName</th><th>LastName</th><th>E-mail</th><th>Department</th><th>Salary</th><th></th></tr>

    <c:forEach var="employee" items="${employees}">
        <tr><td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.email}</td>
            <td>${employee.department}</td>
            <td>${employee.salary}</td>
            <td>
                <a href='<c:url value="/edit?id=${employee.id}" />'>Edit</a> |

                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${employee.id}">
                    <input type="submit" value="Delete">
                </form>

            </td></tr>
    </c:forEach>
</table>
</body>
</html>