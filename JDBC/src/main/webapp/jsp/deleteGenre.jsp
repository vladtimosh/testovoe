<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить Жанр</title>
</head>
<body>
    <h1>Удалить Жанр</h1>
    <form action="delete" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="id">Введите ID жанра для удаления:</label>
        <input type="text" name="id" required>
        <input type="submit" value="Удалить">
    </form>
    <a href="viewAllGenres.jsp">Назад</a>
</body>
</html>