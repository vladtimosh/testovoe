<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Члена</title>
</head>
<body>
    <h1>Добавить Члена</h1>
    <form action="add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="fullName">Полное Имя:</label>
        <input type="text" name="fullName" required>
        <br>
        <label for="membershipDate">Дата Членства (YYYY-MM-DD):</label>
        <input type="date" name="membershipDate" required>
        <br>
        <input type="submit" value="Добавить">
    </form>
    <a href="viewAllMembers.jsp">Назад</a>
</body>
</html>