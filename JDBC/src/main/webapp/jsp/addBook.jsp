<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Книгу</title>
</head>
<body>
    <h1>Добавить Книгу</h1>
    <form action="add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="title">Название:</label>
        <input type="text" name="title" required>
        <br>
        <label for="publicationDate">Дата публикации (YYYY-MM-DD):</label>
        <input type="date" name="publicationDate" required>
        <br>
        <label for="genreId">ID Жанра:</label>
        <input type="number" name="genreId" required>
        <br>
        <input type="submit" value="Добавить">
    </form>
    <a href="viewAllBooks.jsp">Назад</a>
</body>
</html>