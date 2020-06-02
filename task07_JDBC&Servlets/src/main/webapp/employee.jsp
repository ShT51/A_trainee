<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find employee</title>
</head>
<body>

<form method="get" action='<c:url value="/get_employee" />' style="display:inline;">
    <label for="email">E-mail:</label><br>
    <input id="email" name="email" value="example@email.com" type="text" style="color: dimgrey">
    <input type="submit" value="Find">
</form>

<p>
<table>
    <tr>
        <th>FirstName</th>
        <th>LastName</th>
        <th>E-mail</th>
        <th>Department</th>
        <th>Salary</th>
        <th></th>
    </tr>

    <td>${employee.firstName}</td>
    <td>${employee.lastName}</td>
    <td>${employee.email}</td>
    <td>${employee.department}</td>
    <td>${employee.salary}</td>
    </tr>
</table>
</p>

<p><a href='<c:url value="/index" />'>Return to main page</a></p>
</body>
</html>