<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add new employee</title>
</head>
<body>
<h3>New employee</h3>
<form method="post">
    <label>First Name</label><br>
    <input name="firstName"/><br><br>
    <label>Last Name</label><br>
    <input name="lastName"/><br><br>
    <label>E-mail</label><br>
    <input name="email"/><br><br>
    <label>Department</label><br>
    <input name="department"/><br><br>
    <label>Salary</label><br>
    <input name="salary" type="number" min="100"/><br><br>
    <input type="submit" value="Save"/>
</form>
<p><a href='<c:url value="/index" />'>Return to main page</a></p>
</body>
</html>
