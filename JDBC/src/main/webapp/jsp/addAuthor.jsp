<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Автора</title>
</head>
<body>
    <h1>Добавить Автора</h1>
    <form action="add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="name">Имя:</label>
        <input type="text" name="name" required>
        <br>
        <label for="birthDate">Дата рождения (YYYY-MM-DD):</label>
        <input type="date" name="birthDate" required>
        <br>
        <input type="submit" value="Добавить">
    </form>
    <a href="viewAllAuthors.jsp">Назад</a>
</body>
</html>