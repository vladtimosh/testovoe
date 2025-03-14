<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить Автора</title>
</head>
<body>
    <h1>Удалить Автора</h1>
    <form action="delete" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="id">Введите ID автора для удаления:</label>
        <input type="text" name="id" required>
        <input type="submit" value="Удалить">
    </form>
    <a href="viewAllAuthors.jsp">Назад</a>
</body>
</html>