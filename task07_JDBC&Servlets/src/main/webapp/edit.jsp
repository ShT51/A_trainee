<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit employee</title>
</head>
<body>
<h3>Edit employee</h3>
<form method="post">
    <input type="hidden" value="${employee.id}" name="id" />
    <label>First Name</label><br>
    <input name="firstName" value="${employee.firstName}" /><br><br>
    <label>Last Name</label><br>
    <input name="lastName" value="${employee.lastName}" /><br><br>
    <label>E-mail</label><br>
    <input name="email" value="${employee.email}" /><br><br>
    <label>Department</label><br>
    <input name="department" value="${employee.department}" /><br><br>
    <label>Salary</label><br>
    <input name="salary" value="${employee.salary}" type="number" min="100" /><br><br>
    <input type="submit" value="Send" />
</form>
<p><a href='<c:url value="/index" />'>Return to main page</a></p>
</body>
</html>