<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Жанр</title>
</head>
<body>
    <h1>Добавить Жанр</h1>
    <form action="add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="genreName">Название жанра:</label>
        <input type="text" name="genreName" required>
        <br>
        <input type="submit" value="Добавить">
    </form>
    <a href="viewAllGenres.jsp">Назад</a>
</body>
</html>